package com.codermi.common.base.cache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;

/**
 * @author qiudm
 * @date 2018/9/10 15:03
 * @desc
 */
public abstract class CacheFactory {

    public CacheFactory() {

    }

    public static CacheManager getCacheManager() {
        return CacheManagerBuilder.newCacheManagerBuilder().build(true);
    }

}
