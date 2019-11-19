package com.example.momo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.momo.R;
import com.example.momo.activitys.HomeActivity;
import com.example.momo.base.BaseActivity;
import com.example.momo.base.LazyFragment;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.SmartViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeFragment extends LazyFragment {

    @BindView(R.id.home_viewpager)
    SmartViewPager homeViewpager;


    private TabLayout tabLayout;
    private BaseActivity context;
    private ArrayList<LazyFragment> listFragments;


    public HomeFragment(TabLayout t, BaseActivity c) {

        tabLayout = t;
        context = c;

    }


    @Override
    protected void loadData() {


    }

    @Override
    protected boolean enableRefush() {
        return true;
    }

    @Override
    public int getLayoutById() {
        return R.layout.home_layout;
    }

    @Override
    public boolean useCache() {
        return false;
    }

    @Override
    public void initData(boolean b) {
//        swipeRefreshlayout.setColorSchemeColors(Color.parseColor("#F84583"));
        listFragments = getFragments();
        ArrayList<String> tab_title = new ArrayList<String>();
        tab_title.add("直播");
        tab_title.add("推荐");
        tab_title.add("追番");
        tab_title.add("新闻");
        tab_title.add("热门");

        tab_title.add("十周年");


        for (int i = 0; tab_title.size() < 0; i++) {


            tabLayout.getTabAt(i).setText(tab_title.get(i));

        }

        homeViewpager.setAdapter(new FragmentPagerAdapter(context.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return listFragments.get(position);
            }

            @Override
            public int getCount() {
                return tab_title.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tab_title.get(position);
            }
        });

        tabLayout.setupWithViewPager(homeViewpager);

        homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==1){

                    swipeRefreshLayout.setEnabled(false);
                }else {
                    swipeRefreshLayout.setEnabled(true);
                }
                LogUtil.e("statstatestatee"+state);

            }
        });
        homeViewpager.setCurrentItem(1);
        int  h=((HomeActivity)context).getToolbarHight();
//        homeViewpager.getLayoutParams().height= CommonUtil.getScreenPix(context)[1]-CommonUtil.getStatusBarHeight(context)-h*2;
//        homeViewpager.requestLayout();



    }

    private ArrayList<LazyFragment> getFragments() {
        ArrayList<LazyFragment> listFragments = new ArrayList<LazyFragment>();
        HomeFragmentRe re = new HomeFragmentRe();
        HomeFragmentNews news = new HomeFragmentNews();
        HomeFragmentHot hot = new HomeFragmentHot();
        HomeFragmentLive live = new HomeFragmentLive();
        HomeFragmentZhuifan fan = new HomeFragmentZhuifan();
        HomeFragmentZhuifan fan1 = new HomeFragmentZhuifan();
        HomeFragmentZhuifan fan2 = new HomeFragmentZhuifan();
        HomeFragmentZhuifan fan3 = new HomeFragmentZhuifan();
        listFragments.add(live);
        listFragments.add(re);
        listFragments.add(fan);
        listFragments.add(news);
        listFragments.add(hot);
        listFragments.add(fan1);
        listFragments.add(fan2);
        listFragments.add(fan3);

        return listFragments;


    }

    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.e("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        LogUtil.e("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LogUtil.e("onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        LogUtil.e("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtil.e("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtil.e("onDetach");
        super.onDetach();
    }

    @Override
    public void onResume() {
  LogUtil.e("onResume");

        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {

        super.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {

        swipeRefreshLayout.setEnabled(!hidden);

        super.onHiddenChanged(hidden);
    }
}
