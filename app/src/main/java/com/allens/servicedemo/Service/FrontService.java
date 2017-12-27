package com.allens.servicedemo.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.allens.servicedemo.MainActivity;
import com.allens.servicedemo.R;

/**
 * 描述:前台服务
 * <p>
 * Created by allens on 2017/12/27.
 */

public class FrontService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate");

        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        //新建Builer对象
        Notification.Builder builer = new Notification.Builder(this);
        //设置通知的标题
        builer.setContentTitle("前台服务通知的标题")
                //设置通知的内容
                .setContentText("前台服务通知的内容")
                //设置通知的图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置点击通知后的操作
                .setContentIntent(pendingIntent);
        //将Builder对象转变成普通的notification
        Notification notification = builer.getNotification();
        //让Service变成前台Service,并在系统的状态栏显示出来
        startForeground(1, notification);
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
