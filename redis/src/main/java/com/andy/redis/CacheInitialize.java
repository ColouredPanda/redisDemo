package com.andy.redis;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Andy on 2018/11/27.
 */

public class CacheInitialize {

    private static CacheConfig sCacheConfig;

    private CacheInitialize() {
    }

    public static void init(Context context){
        sCacheConfig = Room.databaseBuilder(context.getApplicationContext(),CacheConfig.class,"redis4"+context.getPackageName())
                .allowMainThreadQueries()
                .build();

        MemoryDataCenter.init();
    }

    public static CacheConfig getCacheConfig(){
        if(sCacheConfig ==null){
            throw new NullPointerException("CacheInitialize.init(context) has not call, remember call this function in your Application.class");
        }
        return sCacheConfig;
    }
}
