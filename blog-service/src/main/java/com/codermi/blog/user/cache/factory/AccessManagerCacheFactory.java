package com.codermi.blog.user.cache.factory;

import com.codermi.blog.user.cache.data.dto.AccessToken;
import com.codermi.common.base.cache.CacheFactory;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * @author qiudm
 * @date 2018/9/10 15:06
 * @desc
 */
@Deprecated
public class AccessManagerCacheFactory extends CacheFactory {

    private static final String ACCESS_TOKEN_CACHE = "accessToken";
    /**
     * 内存中最多存放的个数
     */
    private static final long HEAP_ENTRIES = 1000;

    private static final Byte[] LOCKS = new Byte[0];

    private volatile static Cache<String, AccessToken> accessTokenCache = null;

    public AccessManagerCacheFactory() {

    }

    public static final Cache getAccessTokenCache() {
        if (accessTokenCache == null) {
            accessTokenCache = getCacheManager()
                    .getCache(ACCESS_TOKEN_CACHE, String.class, AccessToken.class);
            synchronized (LOCKS) {
                if (accessTokenCache == null) {
                    accessTokenCache = getCacheManager().createCache(ACCESS_TOKEN_CACHE, CacheConfigurationBuilder
                            .newCacheConfigurationBuilder(String.class, AccessToken.class,
                                    ResourcePoolsBuilder.heap(HEAP_ENTRIES)));
                }
            }
        }
        return accessTokenCache;
    }

}
