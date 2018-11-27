package com.andy.redis;

/**
 * Created by Andy on 2018/11/27.
 */

public class CacheService {

    private CacheService() {
    }

    private static CacheDao getRepository() {
        return CacheSession.get().getRedisDao();
    }

    private static MemoryDataCenter getMemoryRepository() {
        return MemoryDataCenter.get();
    }

    /**
     * 设置缓存
     * PS:如果存在就更新
     */
    public static void set(String key, String value) {
        CacheEntity entity;
        entity = getRepository().findByKey(key);

        if (entity == null) {
            entity = new CacheEntity();
            entity.setKey(key);
            entity.setValue(value);
            getRepository().insert(entity);
        } else {
            entity.setValue(value);
            getRepository().update(entity);
        }
        //添加到内存缓存中
        getMemoryRepository().insert(entity);
    }

    /**
     * 获取缓存
     */
    public static String get(String key, String def) {
        CacheEntity memEntity = getMemoryRepository().get(key);
        CacheEntity entity = memEntity!=null?memEntity:getRepository().findByKey(key);
        if (entity == null) {
            return def;
        }
        if(memEntity==null){//如果内存缓存中不存在该条目，but数据中存在，则把从数据库中拿到的条目插入到内存缓存中
            getMemoryRepository().insert(entity);
        }
        return entity.getValue();
    }

    /**
     * 删除缓存
     */
    public static void delete(String key) {
        CacheEntity entity = getMemoryRepository().get(key);
        if(entity!=null){
            //如果内存缓存中存在该条目，则删除
            getMemoryRepository().delete(entity);
        }else{
            entity = getRepository().findByKey(key);
        }
        if (entity != null) {
            getRepository().delete(entity);
        }
    }

    /**
     * 删除全部缓存
     */
    public static void clearAll() {
        CacheEntity[] entities = getRepository().findAll();
        if (entities != null&&entities.length>0) {
            getRepository().delete(entities);
        }
        //清除内存缓存
        getMemoryRepository().clear();
    }
}
