package com.codermi.sercurity.cache;

import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.sercurity.cache.factory.AccessManagerCacheFactory;
import org.ehcache.Cache;

/**
 * @author qiudm
 * @date 2018/9/11 11:06
 * @desc
 */
public class AccessTokenCache<T extends AccessToken> {

    private Cache cache = null;

    public AccessTokenCache() {
        cache = AccessManagerCacheFactory.getAccessTokenCache();
    }


    public void put(String token, T accessToken) {
        this.cache.put(token, accessToken);
    }

    public T get(String token) {
        return (T)this.cache.get(token);
    }

}
