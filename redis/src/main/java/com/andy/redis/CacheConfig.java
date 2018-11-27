package com.andy.redis;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Andy on 2018/11/27.
 */
@Database(entities = {CacheEntity.class}, version = 1, exportSchema = false)
public abstract class CacheConfig extends RoomDatabase {
    public abstract CacheDao getRedisDao();
}
