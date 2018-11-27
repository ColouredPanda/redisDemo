package com.andy.redis;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Andy on 2018/11/27.
 */
@Entity(tableName = "cache")
public class CacheEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String key;
    public String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
