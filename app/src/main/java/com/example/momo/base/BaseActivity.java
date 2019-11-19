package com.example.momo.base;


import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.momo.dao.entity.News;
import com.example.momo.receiver.NetworkBroadcastReceiver;
import com.example.momo.utils.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public  abstract  class  BaseActivity extends RxAppCompatActivity {

    private Unbinder bind;
    private NetworkBroadcastReceiver networkBroadcastReceiver;
    public SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        verifyStoragePermissions(this);
        setContentView(getLayoutID());

        bind=ButterKnife.bind(this);
        initViews(savedInstanceState);
        initToolBar();

    }

    public abstract void initViews(Bundle savedInstanceState) ;

    /**
     * 初始化toolbar
     */
    public abstract void initToolBar();

    public void initRecyclerView() {


    }

    public void loaddingData() {


}

public void showProgress(){


}

    public void hideProgress() {


    }

    /**
     * 初始化refreshLayout
     */
    public void initRefreshLayout() {
    }

    /**
     * 设置数据显示
     */
    public void finishTask() {
    }


    protected abstract int getLayoutID();


    @Override
    protected void onDestroy() {

        super.onDestroy();
        EventBus.getDefault().unregister(this);
        bind.unbind();

        unregisterReceiver(networkBroadcastReceiver);

    }
    protected void intent2Activity(Class<? extends BaseActivity> tarActivity,int in,int on) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
        overridePendingTransition(in,on);
    }


    protected void showToast(String msg) {

        ToastUtils.showToast(this, msg, Toast.LENGTH_SHORT);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
public void getText(News.NewslistBean newslistBean){




}
    @Override
    protected void onResume() {

  if(networkBroadcastReceiver==null){
      networkBroadcastReceiver = new NetworkBroadcastReceiver();
      IntentFilter filter = new IntentFilter();
      filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
      filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
      filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
      registerReceiver(networkBroadcastReceiver, filter);
  }



        super.onResume();
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    public static void verifyStoragePermissions(BaseActivity activity) {


        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
