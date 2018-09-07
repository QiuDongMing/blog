package com.codermi.blog.user.service.impl;
import com.alibaba.fastjson.JSON;
import com.codermi.blog.common.constants.Constants;
import com.codermi.blog.common.service.IIdIndexService;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.dao.IUserDao;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.user.data.request.RegisterRequest;
import com.codermi.blog.user.enums.UserEnum;
import com.codermi.blog.user.service.ISecurityService;
import com.codermi.blog.user.utils.KeyBuilder;
import com.codermi.blog.user.utils.UserCacheUtil;
import com.codermi.common.base.enums.ErrorCode;
import com.codermi.common.base.utils.BeanUtil;
import com.codermi.common.base.utils.MD5Util;
import com.codermi.common.base.utils.StringUtils;
import com.codermi.common.base.utils.ThreadPoolUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author qiudm
 * @date 2018/9/6 15:31
 * @desc
 */
@Service
public class SecurityServiceImpl implements ISecurityService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IIdIndexService idIndexService;

    @Autowired
    private UserCacheUtil userCacheUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public AccessToken loginByNickNamePassword(String nickName, String password) {
        User user = userDao.getByNickName(nickName);
        try {
            this.validateDbUser(user);
            if (Objects
                    .equals(MD5Util.md5Hex(password, Constants.PASSWORD_SALT), user.getPassword())) {
                UserInfo userInfo = createUserInfo(user);
                userCacheUtil.put(user.getUserId(), userInfo);
                AccessToken accessToken = setAccessToken(user);
                cacheAccessToken(accessToken);
                ThreadPoolUtils.execute(() -> cacheUserIdToken(accessToken.getUserId(), accessToken.getToken()));
                return accessToken;
            }
            throw new ServiceException("用户名或密码错误");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 验证用户
     * @param user
     */
    private void validateDbUser(User user) {
        if (Objects.isNull(user)) {
            throw new ServiceException(ErrorCode.USER_NOT_EXIT.getErrorCode(), ErrorCode.USER_NOT_EXIT.getErrMsg());
        }

        if (Objects.equals(user.getStatus(), UserEnum.UserStatus.DISABLED.getStatus())) {
            throw new ServiceException(ErrorCode.USER_DISABLED.getErrorCode(), ErrorCode.USER_DISABLED.getErrMsg());
        }
    }


    @Override
    public void register(RegisterRequest registerRequest) {
        String nickName = registerRequest.getNickName();
        User dbUser = userDao.getByNickName(nickName);
        if (dbUser != null) {
            throw new ServiceException("该昵称已被注册");
        }
        try {
            User user = new User();
            user.setNickName(nickName);
            user.setUserType(UserEnum.UserType.USER.getType());
            user.setPassword(MD5Util.md5Hex(registerRequest.getPassword(), Constants.PASSWORD_SALT));
            user = initUser(user);
            userDao.saveUser(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }

    /**
     * 初始化用户信息
     * @param user
     * @return
     */
    private User initUser(User user) throws Exception {
        user.setRoles(Lists.newArrayList(UserEnum.UserRole.ROLE_USER.name()));
        user.setCreateTime(System.currentTimeMillis());
        user.setUserId(idIndexService.getNextUserId());
        user.setOpenId(StringUtils.randomUUID());
        return user;
    }


    private AccessToken setAccessToken(User user) {
        String token = StringUtils.randomUUID();
        AccessToken accessToken = BeanUtil.copy(user, AccessToken.class);
        accessToken.setToken(token);
        return accessToken;
    }

    /**
     * 缓存accessToken
     * @param accessToken
     */
    private void cacheAccessToken(AccessToken accessToken) {
        String token = accessToken.getToken();
        redisTemplate.opsForValue().set(
                KeyBuilder.getCacheKey(Constants.CacheKeyPre.TOKEN_USER_LOGIN, token),
                JSON.toJSONString(accessToken),
                Constants.Expire.HOUR1,
                TimeUnit.SECONDS
        );
    }

    /**
     * 缓存用户的token，并且让以前的token失效
     * @param userId
     * @param token
     */
    private void cacheUserIdToken(String userId, String token) {
        String userIdTokenKey = KeyBuilder.getCacheKey(Constants.CacheKeyPre.USERID_TOKEN, userId);
        String cacheUserToken = redisTemplate.opsForValue().getAndSet(userIdTokenKey, token);
        if (StringUtils.isNotEmpty(cacheUserToken)) {
            deleteUserToken(cacheUserToken);
        }
    }

    /**
     * 删除登录的用户token
     * @param token
     */
    private void deleteUserToken(String token) {
        String loginUserToken = KeyBuilder.getCacheKey(Constants.CacheKeyPre.TOKEN_USER_LOGIN, token);
        redisTemplate.delete(loginUserToken);
    }



    private UserInfo createUserInfo(User user) {
        return BeanUtil.copy(user, UserInfo.class);
    }

}
