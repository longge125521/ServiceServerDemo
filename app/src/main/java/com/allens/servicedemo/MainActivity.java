package com.allens.servicedemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.allens.servicedemo.Service.BindService;
import com.allens.servicedemo.Service.FrontService;
import com.allens.servicedemo.Service.LocalService;

public class MainActivity extends AppCompatActivity {

    private BindService.MyBinder binder;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * 启动式
         */
        findViewById(R.id.local_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocalService.class);
                startService(intent);
            }
        });
        findViewById(R.id.local_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocalService.class);
                stopService(intent);
            }
        });

        /***
         * 前台服务
         */
        findViewById(R.id.front_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrontService.class);
                startService(intent);
            }
        });
        findViewById(R.id.front_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrontService.class);
                stopService(intent);
            }
        });


        /***
         * 绑定式服务
         */
        //绑定服务
        findViewById(R.id.bind_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BindService.class);

                connection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.e("TAG", "绑定服务成功了");
                        binder = (BindService.MyBinder) service;
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.e("TAG", "绑定服务断开了");
                    }
                };
                //通过intent的extras进行值的传递
                intent.putExtra("data1", "前台的界面绑定服务了");
                bindService(intent, connection, Service.BIND_AUTO_CREATE);
            }
        });


        //获取服务中传过来的参数
        findViewById(R.id.bind_getData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder != null) {
                    int number = binder.getNumber();
                    Log.e("TAG", "NUMBER---->" + number);
                }
            }
        });


        //解除绑定
        findViewById(R.id.bind_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解除绑定的方法
                if (connection != null) {
                    unbindService(connection);
                    //自己控制引用的myBinder对象
                    binder = null;
                }
            }
        });
    }
}
