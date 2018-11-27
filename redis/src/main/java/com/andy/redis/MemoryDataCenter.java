package com.andy.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 内存数据中心
 * Created by Andy on 2018/11/27.
 */

public class MemoryDataCenter {

    private Map<String,CacheEntity> mMemoryCache;
    private static MemoryDataCenter sMemoryDataCenter;

    private MemoryDataCenter() {
        mMemoryCache = new HashMap<>();
        CacheEntity[] all = CacheSession.get().getRedisDao().findAll();
        for (CacheEntity entity :all){
            mMemoryCache.put(entity.key,entity);
        }
    }

    public static void init(){
        synchronized (MemoryDataCenter.class){
            if(sMemoryDataCenter ==null){
                sMemoryDataCenter = new MemoryDataCenter();
            }
        }
    }

    public static MemoryDataCenter get(){
        if(sMemoryDataCenter ==null){
            throw new NullPointerException("MemoryDataCenter.init() has not call, remember call this function in the function that name is init of CacheInitialize.class");
        }
        return sMemoryDataCenter;
    }

    public void insert(CacheEntity... entities){
        for(CacheEntity entity:entities){
            CacheEntity en = mMemoryCache.get(entity);
            if(en==null){
                mMemoryCache.put(entity.key,entity);
            }else{
                en.setValue(entity.getValue());
            }
        }
    }

    public void delete(CacheEntity... entities){
        for(CacheEntity entity:entities){
            mMemoryCache.remove(entity.key);
        }
    }

    public void update(CacheEntity... entities){
        for(CacheEntity entity:entities){
            mMemoryCache.put(entity.key,entity);
        }
    }

    public void clear(){
        mMemoryCache.clear();
    }

    public CacheEntity get(String key){
       return mMemoryCache.get(key);
    }

    public List<CacheEntity> getAll(){
        ArrayList<CacheEntity> entities = new ArrayList<>();
        Set<String> keys = mMemoryCache.keySet();
        for(String key:keys){
            entities.add(mMemoryCache.get(key));
        }
        return entities;
    }


}
