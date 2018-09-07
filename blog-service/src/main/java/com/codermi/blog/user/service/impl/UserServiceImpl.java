package com.codermi.blog.user.service.impl;

import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.dao.IUserDao;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.utils.UserCacheUtil;
import com.codermi.common.base.utils.BeanUtil;
import com.codermi.common.base.utils.StringUtils;
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
