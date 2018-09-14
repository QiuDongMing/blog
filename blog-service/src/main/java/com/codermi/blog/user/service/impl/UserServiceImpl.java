package com.codermi.blog.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.codermi.blog.common.constants.Constants;
import com.codermi.blog.exception.ServiceException;
import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.dao.IUserDao;
import com.codermi.blog.user.data.po.User;
import com.codermi.blog.user.service.IUserService;
import com.codermi.blog.user.utils.KeyBuilder;
import com.codermi.blog.user.utils.UserCacheUtil;
import com.codermi.common.base.utils.BeanUtil;
import com.codermi.common.base.utils.MapUtil;
import com.codermi.common.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author qiudm
 * @date 2018/6/28 11:34
 * @desc
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserCacheUtil userCacheUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public UserInfo getUserInfo(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        UserInfo userInfo = userCacheUtil.get(userId);
        if (userInfo == null) {
            Map<Object, Object> userInfoMaps = redisTemplate.opsForHash()
                    .entries(KeyBuilder.getCacheKey(Constants.CacheKeyPre.USER_INFO, userId));
            userInfo = JSON.parseObject(JSON.toJSONString(userInfoMaps), UserInfo.class);
            if(userInfo == null) {
                User user = this.getUserByUserId(userId);
                if (Objects.nonNull(user)) {
                    userInfo = BeanUtil.copy(user, UserInfo.class);
                    this.cacheUserInfo(userInfo);
                }
            }
        }
        return userInfo;
    }


    /**
     * 缓存用户信息
     * @param userInfo
     */
    @Override
    public void cacheUserInfo(UserInfo userInfo) {
        try {
            String key = KeyBuilder.getCacheKey(Constants.CacheKeyPre.USER_INFO, userInfo.getUserId());
            Map<String, Object> userInfoMap = MapUtil.beanToMap(userInfo);
            redisTemplate.opsForHash().putAll(key, userInfoMap);
        } catch (Exception e){
            LOGGER.error("cache userInfo failed:", e);
        }
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
