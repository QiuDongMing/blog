package com.coderme.blog.user.cache;

import com.alibaba.fastjson.JSON;
import com.coderme.blog.user.cache.data.dto.BaseUserInfo;
import com.coderme.blog.user.cache.factory.CacheFactory;
import org.ehcache.Cache;
import org.springframework.stereotype.Component;

/**
 * @author qiudm
 * @date 2018/7/20 11:41
 * @desc
 */
@Component
public class UserCache<T extends BaseUserInfo> {

    private Cache cache = null;

    public UserCache() {
        this.cache = CacheFactory.getUserCache();
    }


    public void put(String userId, T user) {
        this.cache.put(userId, user);
    }

    public T get(String userId) {
        return (T)this.cache.get(userId);
    }


    public static void main(String[] args) {

        String userId = "1001";
        BaseUserInfo user = new BaseUserInfo();
        user.setUserId(userId);
        user.setUserName("aaa");

        UserCache userCache = new UserCache();
        userCache.put(userId, user);
        BaseUserInfo user1 = userCache.get(userId);
        System.out.println("user1 = " + JSON.toJSONString(user1));


    }


}
