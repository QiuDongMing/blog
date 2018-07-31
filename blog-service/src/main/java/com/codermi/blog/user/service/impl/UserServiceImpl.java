package com.codermi.blog.user.service.impl;

import com.codermi.blog.common.constants.Constant;
import com.codermi.blog.common.service.IIdIndexService;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.dao.IUserDao;
import com.codermi.blog.user.data.UserOpenInfo;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.user.data.request.RegisterRequest;
import com.codermi.blog.user.enums.UserRole;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.utils.UserCacheUtil;
import com.codermi.common.base.utils.BeanUtil;
import com.codermi.common.base.utils.MD5Util;
import com.codermi.common.base.utils.StringUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/6/28 11:34
 * @desc
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserCacheUtil userCacheUtil;

    @Autowired
    private IIdIndexService idIndexService;

    @Override
    public UserInfo getUserInfo(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        UserInfo userInfo = userCacheUtil.get(userId);
        if (userInfo == null) {
            User user = this.getUserByUserId(userId);
            if (Objects.nonNull(user)) {
                userInfo = BeanUtil.copy(user, UserInfo.class);
            }
        }
        return userInfo;
    }

    @Override
    public UserInfo loginByNickNamePassword(String nickName, String password) {
        User user = userDao.getByNickName(nickName);
        try {
            if (Objects.nonNull(user) && Objects
                    .equals(MD5Util.md5Hex(password, Constant.PASSWORD_SALT), user.getPassword())) {
                UserInfo userInfo = createUserInfo(user);
                userCacheUtil.put(user.getUserId(), userInfo);
                return userInfo;
            }
            throw new ServiceException("用户名或密码错误");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
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
            user.setPassword(MD5Util.md5Hex(registerRequest.getPassword(), Constant.PASSWORD_SALT));
            user = initUser(user);
            userDao.saveUser(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }


    private UserOpenInfo createUserOpenInfo(User user) {
        return BeanUtil.copy(user, UserOpenInfo.class);
    }

    private UserInfo createUserInfo(User user) {
        return BeanUtil.copy(user, UserInfo.class);
    }

    /**
     * 初始化用户信息
     *
     * @param user
     * @return
     */
    private User initUser(User user) throws Exception {
        user.setRoles(Lists.newArrayList(UserRole.Role.ROLE_USER.name()));
        user.setCreateTime(System.currentTimeMillis());
        user.setUserId(idIndexService.getNextUserId());
        user.setOpenId(StringUtils.randomUUID());
        return user;
    }


    private User getUserByOpenId(String openId) {
        if (Objects.isNull(openId)) {
            throw new ServiceException("openId不能为空");
        }
        return userDao.getByOpenId(openId);
    }

    private User getUserByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        return userDao.getByUserId(userId);
    }


}
