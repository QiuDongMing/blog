package com.codermi.blog.user.cache.factory;

import com.codermi.blog.user.cache.data.dto.UserInfo;
import com.codermi.common.base.cache.CacheFactory;
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
public class UserCacheFactory extends CacheFactory {

    private static final String USER_CACHE = "user";
    /**
     * 内存中最多存放的个数
     */
    private static final long HEAP_ENTRIES = 1000;

    public UserCacheFactory() {

    }

    public static final Cache getUserCache() {
        Cache<String, UserInfo> userCache = getCacheManager().getCache(USER_CACHE, String.class, UserInfo.class);
        if (userCache == null) {
            synchronized (USER_CACHE) {
                if (userCache == null) {
                    userCache = getCacheManager().createCache(USER_CACHE, CacheConfigurationBuilder
                            .newCacheConfigurationBuilder(String.class, UserInfo.class,
                                    ResourcePoolsBuilder.heap(HEAP_ENTRIES)));
                }
            }
        }
        return userCache;
    }


}
