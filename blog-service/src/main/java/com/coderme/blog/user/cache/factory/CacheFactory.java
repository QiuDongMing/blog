package com.coderme.blog.user.cache.factory;

import com.coderme.blog.user.cache.data.dto.BaseUserInfo;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * @author qiudm
 * @date 2018/7/20 11:40
 * @desc
 */
public class CacheFactory {

    private static final String USER_CACHE = "user";

    public CacheFactory() {

    }

    public static CacheManager getCacheManager() {
        return CacheManagerBuilder.newCacheManagerBuilder().build(true);
    }

    public static final Cache getUserCache() {
        Cache<String, BaseUserInfo> userCache = getCacheManager().getCache(USER_CACHE, String.class, BaseUserInfo.class);
        if (userCache == null) {
            synchronized (USER_CACHE) {
                if (userCache == null) {
                    userCache = getCacheManager().createCache(USER_CACHE, CacheConfigurationBuilder
                            .newCacheConfigurationBuilder(String.class, BaseUserInfo.class, ResourcePoolsBuilder.heap(100)));
                }
            }
        }
        return userCache;
    }


}
