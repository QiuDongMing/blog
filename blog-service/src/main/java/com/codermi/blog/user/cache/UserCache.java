package com.codermi.blog.user.cache;

import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.blog.user.cache.factory.UserCacheFactory;
import org.ehcache.Cache;

/**
 * @author qiudm
 * @date 2018/7/20 11:41
 * @desc
 */

public class UserCache<T extends UserInfo> {

    private Cache cache = null;

    public UserCache() {
        this.cache = UserCacheFactory.getUserCache();
    }


    public void put(String userId, T user) {
        this.cache.put(userId, user);
    }

    public T get(String userId) {
        return (T)this.cache.get(userId);
    }


}
