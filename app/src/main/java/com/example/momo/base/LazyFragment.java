package com.example.momo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.activitys.HomeActivity;
import com.example.momo.dao.entity.News;
import com.example.momo.interfacee.OnLifeState;
import com.example.momo.utils.LogUtil;
import com.example.momo.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

public abstract class LazyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


  protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        LogUtil.e("lAYZ+savedInstanceState");
        super.onCreate(savedInstanceState);
    }
     @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
     public void fixContent(News news){



     }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //不能这种逻辑  应该到当前fragment时候再绑定事件 不然会被后来的覆盖
         if(getContext() instanceof HomeActivity){
              swipeRefreshLayout=  ( (HomeActivity)getContext()).getSwipeRefreshlayout();
//            if( swipeRefreshLayout!=null){
//                 swipeRefreshLayout .setOnRefreshListener(this);
//
//         }

         }

        View view;

        //1.如果 网络链接成功则  2.或者网络不成功但是想用缓存显示
        if (NetworkUtils.checkNet(getContext()) || useCache()) {
            view = inflater.inflate(getLayoutById(), container, false);
            ButterKnife.bind(this, view);
            initData(true);
        } else {
            //当前无网络 加载错误友好页面
            view = inflater.inflate(R.layout.loadding_erro_layout, container, false);


        }
        return view;
    }

    protected abstract void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout);


    @Override
    public void onResume() {


//        swipeRefreshLayout.setEnabled(true);

        setOnRefreshLitener(swipeRefreshLayout);
        //判断网络  还有是否用缓存 //如果当前没网络 就不执行请求数据操作
        if(useCache()||NetworkUtils.checkNet(getContext())){
            loadData();

   }
        //第一次 还没初始化为null 在改控件初始化 启动。
        if(onLifeState!=null) onLifeState .pause(false);
        //初始化数据，如果没被销毁 每次调用 都要loadding?
     super.onResume();

    }

    @Override
    public void onPause() {

        if(swipeRefreshLayout!=null){

           if(swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);

        }
        if(onLifeState!=null) onLifeState .pause(true);
        LogUtil.e("onResume()->onLifeState .pause( onPause()----------------true");
        super.onPause();
    }

    protected abstract void loadData( );
    protected abstract boolean enableRefush( );
    public  abstract  int  getLayoutById();

    public  abstract void initData(boolean f);
    public  abstract boolean useCache();





    protected OnLifeState onLifeState;
    public void  setOnLifeStateListener(OnLifeState  o){

        onLifeState=o;


    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        LogUtil.e("onRefreshsss");

    }


}
