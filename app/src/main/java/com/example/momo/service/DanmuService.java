package com.example.momo.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.momo.activitys.OtherActvity;
import com.example.momo.utils.LogUtil;

public class DanmuService extends Service {

    private static final String TAG = "MessagerService";

    /**
     * 处理来自客户端的消息，并用于构建Messenger
     */
    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case OtherActvity.MESSAGE_FROM_CLIENT:
                    Log.e(TAG, "receive message from client:" + message.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(message);
                    break;
            }
        }
    }

    /**
     * 构建Messenger对象
     */
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e("service-onBind");
        //将Messenger对象的Binder返回给客户端
        return mMessenger.getBinder();
    }


    @Override
    public void onCreate() {
        LogUtil.d("service+onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("service+onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        LogUtil.d("service+onDestroy");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.d("service+onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }



    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d("service+onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        LogUtil.d("service+onRebind");
        super.onRebind(intent);
    }



}
