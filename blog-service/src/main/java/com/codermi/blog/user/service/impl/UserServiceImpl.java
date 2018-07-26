package com.codermi.blog.user.service.impl;

import com.codermi.blog.common.service.IIdIndexService;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.dao.IUserDao;
import com.codermi.blog.user.data.UserOpenInfo;
import com.codermi.blog.user.data.po.User;
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

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    public UserInfo getBaseUserInfo(String userId) {
        return userCacheUtil.get(userId);
    }

    @Override
    public void setBaseUserInfo(UserInfo userInfo) {
        userCacheUtil.put(userInfo.getUserId(), userInfo);
    }

    @Override
    public UserOpenInfo loginByNickNamePassword(String nickName, String password) {
        User user = userDao.getByNickName(nickName);
        try {
            if (Objects.nonNull(user) && Objects
                    .equals(MD5Util.EncoderByMd5(password), user.getPassword())) {
                return createUserOpenInfo(user);
            }
            throw new ServiceException("用户名或密码错误");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void register(User user) {
        String nickName = user.getNickName();
        User dbUser = userDao.getByNickName(nickName);
        if (dbUser != null) {
            throw new ServiceException("该昵称已被注册");
        }

        try {
            user.setPassword(MD5Util.EncoderByMd5(user.getPassword()));
            user = initUser(user);
            userDao.saveUser(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }

    private UserOpenInfo createUserOpenInfo(User user) {
        return BeanUtil.copy(user, UserOpenInfo.class);
    }

    /**
     * 初始化用户信息
     * @param user
     * @return
     */
    private User initUser(User user) throws Exception {
        user.setRoles(Lists.newArrayList(UserRole.Role.ROLE_USER.name()));
        user.setCreateTime(System.currentTimeMillis());
        user.setUserId(idIndexService.getNextUserId());
        user.setOpenId(MD5Util.EncoderByMd5(StringUtils.randomUUID()));
        return user;
    }




}
