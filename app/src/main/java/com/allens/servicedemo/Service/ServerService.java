package com.allens.servicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 描述:  AIDL 服务端service
 * <p>
 * Created by allens on 2017/12/27.
 */

public class ServerService extends Service {
    private MyBinder myBinder;

    //继承IServer.Stub 并实现其接口
    public class MyBinder extends IServer.Stub {
        @Override
        public void onServer() throws RemoteException {
            Log.e("TAG", "客户端与远程服务通信成功");
            System.out.print("客户端与远程服务通信成功  " + Thread.currentThread().getName());
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "onBind");
        return myBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate");
        myBinder = new MyBinder();
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
