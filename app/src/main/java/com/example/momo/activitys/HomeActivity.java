package com.example.momo.activitys;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.base.LazyFragment;
import com.example.momo.dao.entity.ChaofanBanJiang;
import com.example.momo.fragment.ChannelFragment;
import com.example.momo.fragment.HomeFragment;
import com.example.momo.fragment.MessageFragment;
import com.example.momo.fragment.ShopFragment;
import com.example.momo.manager.PopupwindowManager;
import com.example.momo.manager.ToolbarManger;
import com.example.momo.network.qiniu.QiNiuHelper;
import com.example.momo.presenter.FragmentControl;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.MyFrameLayout;
import com.example.momo.widget.MySwipeRefreshLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {
    @BindView(R.id.contain)
    MyFrameLayout contain;
    @BindView(R.id.navigation)
    BottomNavigationView navigation_bottom;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.user_image)
    SimpleDraweeView userImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.drawer_Layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.swipe_refreshlayout)
    MySwipeRefreshLayout swipeRefreshlayout;
    @BindView(R.id.coordinator_layout)
    RelativeLayout coordinatorLayout;
    @BindView(R.id.search_edit)
    EditText searchEdit;

    private FragmentControl fragmentControl;
    private ToolbarManger tm;
    private ArrayList<LazyFragment> fragments;

    @Override
    public void initViews(Bundle savedInstanceState)   {




        searchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ARouter.getInstance().build("/activitys/SearchActivity").navigation();
            }
        });

        QiNiuHelper niuHelper = new QiNiuHelper();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                niuHelper.upload();

            }
        }).start();

        isExpend();
        UMConfigure.init(this, "5da5dd664ca357fd3900047f"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

//        redPremi();
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);
        tabLayout.setAnimation(new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
            }
        });
        tabLayout.pageScroll(1);
        toolbar.setNavigationIcon(R.drawable.ic_drawer_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(navigationView);
                }

            }
        });


        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });
        //取消滚动条
        disableNavigationViewScrollbars(navigationView);


        tm = new ToolbarManger(toolbar, this, contain);
        initLeftNavigationView();
        initFragments();


        navigation_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home: {
                        ControllAppBar(true);
                        appbarLayout.setNestedScrollingEnabled(true);
                        tabLayout.setVisibility(View.VISIBLE);
                        fragmentControl.fragmentChange(fragments.get(0));
//                        fragmentControl.show(0);
                        tm.setRightIcon(1);


                        break;
                    }
                    case R.id.navigation_channel: {
                        ControllAppBar(true);
                        appbarLayout.setNestedScrollingEnabled(true);
                        tabLayout.setVisibility(View.GONE);
//                        fragmentControl.show(1);
                        fragmentControl.fragmentChange(fragments.get(1));
                        tm.setRightIcon(2);
                        break;
                    }
                    case R.id.navigation_message: {
                        ControllAppBar(true);
                        appbarLayout.setNestedScrollingEnabled(true);
                        tabLayout.setVisibility(View.GONE);
                        fragmentControl.fragmentChange(fragments.get(2));
//                        fragmentControl.show(2);
                        tm.setRightIcon(3);
                        break;
                    }
                    case R.id.navigation_vip: {
                        ControllAppBar(false);
                        fragmentControl.fragmentChange(fragments.get(3));
                        appbarLayout.setNestedScrollingEnabled(false);

                        tm.setRightIcon(4);
                        tabLayout.setVisibility(View.GONE);
//                        fragmentControl.show(3);
                        break;
                    }
                }
                if (isUp) {
                    appbarLayout.setExpanded(true, false);

                }
                return true;
            }
        });


        ViewTreeObserver ovr = toolbar.getViewTreeObserver();
        ovr.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                fix = toolbar.getHeight();
                initViewPager();

            }
        });


//        textWX();

    }


    public static String getChannelName(Context ctx) {
        String channelName = UMUtils.getChannel(ctx);
        return channelName;
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    private void redPremi() {
        if (Build.VERSION.SDK_INT >= 23) {

            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
//            ActivityCompat.requestPermissions(this,PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE,);

            new ShareAction(this)
                    .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                    .withText("hello")//分享内容
                    .setCallback(new UMShareListener() {
                        @Override
                        public void onStart(SHARE_MEDIA share_media) {
                            LogUtil.e("Start///");
                        }

                        @Override
                        public void onResult(SHARE_MEDIA share_media) {
                            LogUtil.e("onResult///");
                        }

                        @Override
                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                            LogUtil.e("onError///" + throwable.toString());
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA share_media) {

                        }
                    })//回调监听器
                    .share();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    public void ControllAppBar(boolean b) {
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();

        if (b) {

            layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        } else {
            layoutParams.setScrollFlags(0);
        }

        toolbar.setLayoutParams(layoutParams);
    }

    public MySwipeRefreshLayout getSwipeRefreshlayout() {


        return swipeRefreshlayout;
    }

    private boolean isUp = false;

    private void isExpend() {

        appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {


                isUp = i < 0 ? true : false;
            }
        });


    }

    private void initViewPager() {

        PopupwindowManager popupwindowManager = new PopupwindowManager(this);
        popupwindowManager.setPopupMenuLayoutId(R.layout.item);
//        popupwindowManager.locationPop(toolbar,0,1300);
//        popupwindowManager.dropDownOfPopu(toolbar,0, CommonUtil.getScreenPix(this)[1]-popupwindowManager.getPopWindow().getHeight());
//        Snackbar.make(toolbar, "有20新消息", 2000)
//                .show();
    }

    //初始化四大fragment
    private void initFragments() {
      fragments = new ArrayList<LazyFragment>();
        fragments.add(new HomeFragment(tabLayout, this));
        fragments.add(new ChannelFragment());
        fragments.add(new MessageFragment());
        fragments.add(new ShopFragment());


        fragmentControl = FragmentControl.getInstance(this, R.id.contain, fragments);
//        fragmentControl.show(0);
        fragmentControl.fragmentChange(fragments.get(0));
    }

    int fix;

    public int getToolbarHight() {
        return fix;

    }

    private void initLeftNavigationView() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.item_home: {


                    }
                    case R.id.item_history: {


                    }
                    case R.id.item_vip: {

                    }
                    case R.id.item_download: {

                    }
                    case R.id.item_favourite: {

                    }
                    case R.id.item_settings: {

                    }
                    case R.id.item_group: {

                    }
                    case R.id.item_tracker: {


                    }


                }
                ChaofanBanJiang chaofanBanJiang =new ChaofanBanJiang();
                chaofanBanJiang.setName("王俊涛");
                chaofanBanJiang.setAge(24);
                EventBus.getDefault().postSticky(chaofanBanJiang);
                intent2Activity(OtherActvity.class, R.anim.activity_in, R.anim.activity_out);

                return true;
            }
        });
    }


    @Override
    public void initToolBar() {

    }


    @Override
    protected int getLayoutID() {
        return R.layout.layout_home;
    }

    /**
     * 全屏
     */
    public void fullScreen() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

    }


    public static void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void changeSmallScreen() {


    }

    @Override
    public void onBackPressed() {

System.exit(0);
        super.onBackPressed();
    }
}

//通知栏完全透明

