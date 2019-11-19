package com.example.momo.activitys;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.dao.entity.RecomendsInfo;
import com.example.momo.fragment.CommentPlayerFragment;
import com.example.momo.fragment.RlativePlayerFragment;
import com.example.momo.manager.PlayerManager;
import com.example.momo.manager.PopupwindowManager;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.Myplayer;
import com.example.momo.widget.SmartViewPager;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlayVedioActivity extends BaseActivity {

    boolean isopen = false;
    @BindView(R.id.player_view)
    Myplayer playerView;


    @BindView(R.id.palyer_tool_bar)
    Toolbar palyerToolBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    //    @BindView(R.id.plaer_refreshlayout)
//    SwipeRefreshLayout plaerRefreshlayout;
    @BindView(R.id.player_vp)
    SmartViewPager playerVp;
    @BindView(R.id.player_icon_layout)
    LinearLayout playerIconlayout;
    @BindView(R.id.palyer_app_bar)
    AppBarLayout palyerAppBar;
    @BindView(R.id.palyer_collpasing)
    CollapsingToolbarLayout palyerCollpasing;
    @BindView(R.id.player_parent)
    CoordinatorLayout playerParent;

    @BindView(R.id.player_down_layout)
    LinearLayoutCompat playerDownLayout;
    @BindView(R.id.player_tab_layout)
    LinearLayout playerTabLayout;

    private SimpleExoPlayer player;
    private SimpleExoPlayer exoPlayer;
    private PlayerManager playerManager;

    @Override
    public void initViews(Bundle savedInstanceState)  {


        initAppLayout();
        initToobarImagView();

        Bundle bundle = getIntent().getExtras();

        RecomendsInfo.ResultBean.UsersBean bean= (RecomendsInfo.ResultBean.UsersBean) getIntent().getSerializableExtra("2");
        String url = bundle.getString("link");
//        LogUtil.e("beassn"+bean.getThumbnail());
        playerManager = new PlayerManager(this);

        playerManager.init();


        playerManager.play("http://192.168.1.100:8080/blibli.mp4", playerView, palyerToolBar);
        playerManager.setControllViewListener(new PlayerControlView.VisibilityListener() {
            @Override
            public void onVisibilityChange(int visibility) {
                   if(exoPlayer.getPlayWhenReady()){
                       palyerToolBar.setVisibility(visibility);

                   }


            }
        });
        exoPlayer = playerManager.getSimpleExoPlayer();
        playerManager.setCollapsingToolbarLayout(palyerCollpasing);
//        exoPlayer.setPlayWhenReady(false);
        tabLayout.addTab(tabLayout.newTab().setText("相关"));
        tabLayout.addTab(tabLayout.newTab().setText("评论"));
        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        palyerToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerManager.isFull()) {
                    playerManager.smallScreen();
                } else {

                    finish();
                }


            }
        });
        initViewpager();
//        playerDownLayout.setVisibility(View.INVISIBLE);

    }
    public void startWindowPop() {

        PopupwindowManager popupwindowManager= new PopupwindowManager(this);
        popupwindowManager.setPopupMenuLayoutId(R.layout.item);
        popupwindowManager.locationPop(tabLayout,0,1300);


    }

    private boolean isFisrt = true;
    private boolean isExpend = true;

    private void initAppLayout() {


        palyerToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


                return false;
            }
        });

        playerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("onClick---");
            }
        });
        palyerCollpasing.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                LogUtil.e("onClick");
                return true;
            }
        });

        playerIconlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.setPlayWhenReady(true);
                playerView.setPreImageLayoutVisility(false);
            }
        });
        palyerAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                //如果已经滚动了 点击则恢复原来状态 并设置播放

                if (i < -palyerAppBar.getTotalScrollRange() + 80 && !exoPlayer.getPlayWhenReady()) {

                    isExpend = true;
                    setGone(false);

                } else {
                    setGone(true);
                    isExpend = false;

                }

            }
        });


    }

    public void setGone(boolean b) {
        int v = b == true ? View.GONE : View.VISIBLE;
        playerIconlayout.setVisibility(v);

    }

    private void initToobarImagView() {
        ViewTreeObserver viewTreeObserver = playerIconlayout.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                playerIconlayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) playerIconlayout.getLayoutParams();

                layoutParams.leftMargin = CommonUtil.getScreenPix(PlayVedioActivity.this)[0] / 2 - palyerToolBar.getCurrentContentInsetLeft() / 2 - playerIconlayout.getMeasuredWidth() / 2;
                playerIconlayout.requestLayout();
            }
        });


    }

    private void initViewpager() {
        ArrayList<Fragment> arrayList = new ArrayList<Fragment>();
        arrayList.add(new RlativePlayerFragment());
        arrayList.add(new CommentPlayerFragment());
        playerVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return arrayList.get(position);
            }

            @Override
            public int getCount() {
                return arrayList.size();
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (playerManager.isFull()) {
            playerManager.smallScreen();
            return false;
        }
        return super.onKeyDown(keyCode, event);

    }

    public Toolbar getPalyerToolBar() {
        return palyerToolBar;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    protected int getLayoutID() {


        return R.layout.play_activity_layout;
    }


    private void setWindowBrightness(int brightness) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightness / 255.0f;
        window.setAttributes(lp);
    }


    @Override
    protected void onResume() {
//        if(player==null){
//            playerManager.init();
//            exoPlayer.setPlayWhenReady(false);
//        }

//        if(!player.getPlayWhenReady()){
//            player.setPlayWhenReady(true);
//        }

        super.onResume();

    }

    @Override
    protected void onPause() {
//        if(player!=null)player.setPlayWhenReady(false);

//        if(player.getPlayWhenReady()==true)

        super.onPause();
    }

    @Override
    protected void onStop() {


        playerManager.reset();


        super.onStop();

    }

    @Override
    protected void onDestroy() {
        playerManager.release();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        fullScreen();
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void changeAppBrightness(int brightness) {
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
        window.setAttributes(lp);
    }

    private void changeWindow() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void fullScreen() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

    }
}
