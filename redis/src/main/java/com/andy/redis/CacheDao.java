package com.andy.redis;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by Andy on 2018/11/27.
 */
@Dao
public interface CacheDao {

    /**
     * 插入
     */
    @Insert
    void insert(CacheEntity... redisEntities);

    /**
     * 更新
     */
    @Update
    void update(CacheEntity... redisEntities);

    /**
     * 删除
     */
    @Delete
    void delete(CacheEntity... redisEntities);

    /**
     * 根据键值查询
     */
    @Query("SELECT * FROM cache where `key`=:key LIMIT 0,1")
    CacheEntity findByKey(String key);

    /**
     * 查询所有
     */
    @Query("SELECT * FROM cache")
    CacheEntity[] findAll();
}
