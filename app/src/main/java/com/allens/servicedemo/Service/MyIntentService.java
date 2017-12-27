package com.allens.servicedemo.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:intentService
 * <p>
 * Created by allens on 2017/12/27.
 */

public class MyIntentService extends IntentService {
    /***
     * 比较规范的action条件,包名+action+功能意义的描述
     */
    private static final String ACTION_FOO = "com.allens.servicedemo.Service.FOO";
    private static final String ACTION_BAZ = "com.allens.servicedemo.Service.BAZ";

    /***
     * 规范的key的写法
     */
    private static final String EXTRA_PARAM1 = "com.allens.servicedemo.Service.PARAM1";
    private static final String EXTRA_PARAM2 = "com.allens.servicedemo.Service.PARAM2";

    public MyIntentService() {
        //给IntentService内部创建的工作线程起名字
        super("MyIntentService");
    }

    /**
     * 外部直接调用该方法就可以启动该服务了(设置了action条件,传递的参数,并且启动了服务)
     *
     * @see IntentService
     */
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }


    public static void startActionBaz(Context context, String param1) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        context.startService(intent);
    }

    /**
     * 进行耗时操作的处理 (当该方法执行完毕以后,服务会自动关闭)
     * 通过action区分不同的耗时操作的逻辑,各自实现各自的逻辑
     * <p/>
     * 从核心代码入手,分析每一行代码表达意思,(可以中间涉及其他方法和成员变量的定义,以此扩展出去查看)
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("TAG", "onHandleIntent");
        if (intent != null) {
            //获取action条件
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                //获取字符串内容
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionBaz(param1);
            }
        }
    }

    //模拟耗时
    private void handleActionBaz(String param1) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("TAG", "param1 " + param1);
    }

    private void handleActionFoo(String param1, String param2) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        Log.e("TAG", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e("TAG", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy");
        super.onDestroy();
    }
}
