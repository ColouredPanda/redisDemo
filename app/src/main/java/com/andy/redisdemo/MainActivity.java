package com.andy.redisdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.andy.redis.CacheInitialize;
import com.andy.redis.CacheService;

public class MainActivity extends AppCompatActivity {
    private View insert, read, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CacheInitialize.init(this);

        insert = findViewById(R.id.insert);
        read = findViewById(R.id.read);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        insert.setOnClickListener(this::onClick);
        read.setOnClickListener(this::onClick);
        update.setOnClickListener(this::onClick);
        delete.setOnClickListener(this::onClick);
        Log.i("CacheService", "time >>> " + System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            CacheService.get("key", "张三");
        }
        Log.i("CacheService", "time >>> " + System.currentTimeMillis());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                CacheService.set("key", "吴欢欢");
                Log.i("CacheService", "insert success");
                break;
            case R.id.read:
                String value = CacheService.get("key", "张三");
                Log.i("CacheService", "获取数据>>>" + value);
                break;
            case R.id.update:
                CacheService.set("key", "李四");
                Log.i("CacheService", "update success");
                break;
            case R.id.delete:
                CacheService.delete("key");
                Log.i("CacheService", "delete success");
                break;
        }
    }
}
