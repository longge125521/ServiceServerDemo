package com.allens.servicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 描述:本地服务
 * <p>
 * Created by allens on 2017/12/27.
 */

public class LocalService extends Service {
    //onBind(...)函数是Service基类中的唯一抽象方法，子类都必须重写实现，
    // 此函数的返回值是针对Bound Service类型的Service才有用的，
    // 在Started Service类型中，此函数直接返回 null 即可
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy");
    }
}
