package com.allens.servicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * 描述: 绑定师服务
 * <p>
 * Created by allens on 2017/12/27.
 */

public class BindService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "onBind");
        //获取Acitivity传递的值
        String value = intent.getStringExtra("data1");
        Log.e("TAG", "获取的内容--->" + value);
        return new MyBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy");
        super.onDestroy();
    }


    //自定义一个Binder类继承 Binder
    public class MyBinder extends Binder {

        //实现自己的逻辑
        public int getNumber() {
            //返回给Activity一个随机数,Activity显示就可以了
            return new Random().nextInt(20);
        }
    }
}
