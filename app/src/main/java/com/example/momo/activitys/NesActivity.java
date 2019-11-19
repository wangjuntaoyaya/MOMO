package com.example.momo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.momo.R;
import com.example.momo.base.BaseFragment;
import com.example.momo.fragment.NewsFragment;
import com.example.momo.fragment.TabFragment;
import com.example.momo.utils.LogUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NesActivity extends AppCompatActivity {

        @BindView(R.id.viewpager_image)
        ImageView viewpagerImage;
    @BindView(R.id.viewpagercontent)
    ViewPager viewpagercontent;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private FragmentPagerAdapter mAdapter;

    @Override
    public void onAttachedToWindow() {

        super.onAttachedToWindow();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news_layout);
        ButterKnife.bind(this);

        LogUtil.e((tab == null) + "");


        ArrayList<BaseFragment> arrf = new ArrayList<BaseFragment>();
        arrf.add(new NewsFragment());
        arrf.add(new NewsFragment());
        arrf.add(new NewsFragment());
        TabFragment[] mFragments = new TabFragment[3];
        for (int i = 0; i < 3; i++)
        {
            mFragments[i] = (TabFragment) TabFragment.newInstance("dddsd");
        }




        ArrayList<String> titls = new ArrayList<String>();
        titls.add("头条");
        titls.add("热点");
        titls.add("娱乐");
        titls.add("头条");
        titls.add("热点");
        titls.add("娱乐");   titls.add("头条");
        titls.add("热点");
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.getTabAt(0).setText("头条");
        tab.getTabAt(1).setText("热点");
        tab.getTabAt(2).setText("娱乐");
        tab.getTabAt(3).setText("头条");
        tab.getTabAt(4).setText("热点");
        tab.getTabAt(5).setText("娱乐");
        tab.getTabAt(6).setText("娱乐");
        tab.getTabAt(7).setText("娱乐");
//        Viewpager_Adapter v=  new Viewpager_Adapter(titls);
//       viewpagercontent.setAdapter( v);
//        viewpagercontent.setAdapter(mAdapter);
//        viewpagercontent.setAdapter(new NewsFragmentAdpater(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, arrf));
        viewpagercontent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return titls.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titls.get(position);
            }
        });
        tab.setupWithViewPager(viewpagercontent);


        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//                Log.e("juntao",i+"  appBarLayout.getTotalScrollRange()"+  appBarLayout.getTotalScrollRange());

            }
        });

        viewpagerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("点击");
            }
        });
    }
}
