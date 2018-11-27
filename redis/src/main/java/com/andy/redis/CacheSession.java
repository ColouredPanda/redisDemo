package com.andy.redis;

/**
 * Created by Andy on 2018/11/27.
 */

public class CacheSession {

    private CacheSession() {
    }

    public static CacheConfig get(){
        return CacheInitialize.getCacheConfig();
    }
}
