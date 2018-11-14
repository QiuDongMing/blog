package com.codermi.blog.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.auth.dao.ISecurityDao;
import com.codermi.blog.auth.data.po.Account;
import com.codermi.blog.auth.enums.AccountEnum.*;
import com.codermi.blog.auth.service.ISecurityService;
import com.codermi.blog.common.constants.Constants;
import com.codermi.blog.common.service.IIdIndexService;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.dao.IPermissionDao;
import com.codermi.blog.user.dao.IRoleDao;
import com.codermi.blog.user.dao.IUserDao;
import com.codermi.blog.user.data.po.Permission;
import com.codermi.blog.user.data.po.Role;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.user.data.request.RegisterRequest;
import com.codermi.blog.user.enums.UserEnums.*;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.utils.UserCacheUtil;
import com.codermi.common.base.enums.ErrorCode;
import com.codermi.common.base.support.KeyBuilder;
import com.codermi.common.base.utils.BeanUtil;
import com.codermi.common.base.utils.EncryUtils;
import com.codermi.common.base.utils.StringUtils;
import com.codermi.common.base.utils.ThreadPoolUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author qiudm
 * @date 2018/11/13 14:23
 * @desc
 */
@Service
public class SecurityServiceImpl implements ISecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private IUserDao userDao;
    @Autowired
    private ISecurityDao securityDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissionDao permissionDao;
    @Autowired
    private IIdIndexService idIndexService;
    @Autowired
    private UserCacheUtil userCacheUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private IUserService userService;


    @Override
    public AccessToken loginByUserNamePassword(String username, String password, Integer userType) {
        User user = userDao.getByUserNameAndType(username, userType);
        try {
            this.validateDbUser(user);
            Account account = securityDao.getByAccountNumAndUserType(username, userType);
            if (account == null) {
                throw new ServiceException(ErrorCode.ACCOUNT_NOT_EXIT);
            }
            if (Objects
                    .equals(EncryUtils.md5Hex(password, Constants.PASSWORD_SALT), account.getPassword())) {
                UserInfo userInfo = createUserInfo(user);
                List<Permission> userPermissions = getUserPermissions(user.getUserId());
                Set<String> perms = Sets.newHashSet();
                if (CollectionUtils.isNotEmpty(userPermissions)) {
                    userPermissions.forEach(permission -> {
                        perms.add(permission.getPerms());
                    });
                }
                userInfo.setPerms(perms);
                userCacheUtil.put(user.getUserId(), userInfo);
                AccessToken accessToken = setAccessToken(user);
                cacheAccessToken(accessToken);
                ThreadPoolUtils.execute(() -> {
                    cacheUserIdToken(accessToken.getUserId(), accessToken.getToken());
                    userService.cacheUserInfo(userInfo);
                });
                return accessToken;
            }
            throw new ServiceException(ErrorCode.BAD_USERNAME_PASSWORD);
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

        if (Objects.equals(user.getStatus(), UserStatus.DISABLED.getStatus())) {
            throw new ServiceException(ErrorCode.USER_DISABLED.getErrorCode(), ErrorCode.USER_DISABLED.getErrMsg());
        }
    }


    @Override
    public void register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername().trim();
        Account dbAccount = securityDao.getByAccountNumAndUserType(username, UserType.USER.getType());

        if (dbAccount != null) {
            throw new ServiceException("该账号已被注册");
        }
        try {
            Account account = new Account();
            String accountId = StringUtils.randomUUID();
            long millis = System.currentTimeMillis();
            account.setCreateTime(millis);
            account.setUpdateTime(millis);
            account.setAccountNum(username);
            account.setAccountId(accountId);
            account.setAccountType(AccountType.username.name());
            account.setUserType(UserType.USER.getType());
            account.setPassword(EncryUtils.md5Hex(registerRequest.getPassword(), Constants.PASSWORD_SALT));
            securityDao.insertAccount(account);

            User user = new User();
            user.setAccountId(accountId);
            user.setUserType(UserType.USER.getType());
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user = initUser(user);
            userDao.saveUser(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public AccessToken getAccessToken(String token) {
        AccessToken accessToken = null;
        if(token == null) return accessToken;
        String accessTokenStr = redisTemplate
                .opsForValue().get(KeyBuilder.getCacheKey(Constants.CacheKeyPre.TOKEN_USER_LOGIN, token));
        accessToken = JSON.parseObject(accessTokenStr, AccessToken.class);
        return accessToken;
    }

    /**
     * 初始化用户信息
     * @param user
     * @return
     */
    private User initUser(User user) throws Exception {
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


    /**
     * 获取用户的权限列表
     * @param userId
     * @return
     */
    @Override
    public List<Permission> getUserPermissions(String userId) {
        List<Permission> permissions = Lists.newArrayList();
        User user = userDao.getByUserId(userId);

        List<String> roleIds = user.getRoleIds();
        if (CollectionUtils.isEmpty(roleIds)) {
            return permissions;
        }

        List<ObjectId> roleObjIds = Lists.newArrayList();
        for (String roleId : roleIds ) {
            roleObjIds.add(new ObjectId(roleId));
        }

        List<ObjectId> permissionObjIds = Lists.newArrayList();
        List<Role> roles = roleDao.getByIds(roleObjIds);
        if (CollectionUtils.isNotEmpty(roles)) {
            for(Role role : roles) {
                for (String permId : role.getPermissionIds()) {
                    permissionObjIds.add(new ObjectId(permId));
                }
            }
        }
        return permissionDao.getByIds(permissionObjIds);
    }




}
