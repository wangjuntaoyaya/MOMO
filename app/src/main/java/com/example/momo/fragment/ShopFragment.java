package com.example.momo.fragment;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.adpater.Viewpager_Adapter;
import com.example.momo.base.BaseActivity;
import com.example.momo.base.LazyFragment;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.CircleIndicateLayout;
import com.example.momo.widget.MyHeadViewPager;
import com.example.momo.widget.SmartViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class ShopFragment extends LazyFragment {


    @BindView(R.id.vip_radio_group)
    RadioGroup vipRadioGroup;

    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;

    @BindView(R.id.shop_tab)
    TabLayout shopTab;
    @BindView(R.id.shop_baobu_vp)
    SmartViewPager shopBaobuVp;
    @BindView(R.id.vp)
    MyHeadViewPager vp;
    @BindView(R.id.indicator_circle)
    CircleIndicateLayout indicatorCircle;
    @BindView(R.id.shop_text01)
    TextView shopText01;
    @BindView(R.id.shop_text02)
    TextView shopText02;
    @BindView(R.id.shop_text03)
    TextView shopText03;


    @Override
    protected void loadData() {

    }

    private ArrayList<String> initTab() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("推荐");
        arrayList.add("商品");
        arrayList.add("美图");
        arrayList.add("情报");
        return arrayList;

    }

    private ArrayList<LazyFragment> initFragments() {
        ArrayList<LazyFragment> arrayList = new ArrayList<LazyFragment>();
        arrayList.add(new ShopFragmentBaobuLiu());
        arrayList.add(new ShopFragmentSomeThing());
        arrayList.add(new ShopFragmentPicture());
        return arrayList;
    }

    private ArrayList<String> initUris() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/ec78f3f431441b94099944c0b070804d610418e0.jpg@2320w_664h.jpg");

        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/8ffddd89a3e95a1516a92065e6248366937111cc.jpg@2320w_664h.jpg");
        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/897074021d678a5be9d8a442f4c6aedae6164e7c.jpg@2320w_664h.jpg");

        return arrayList;

    }

    @Override
    protected boolean enableRefush() {

        return true;
    }

    @Override
    public int getLayoutById() {
        return R.layout.layout_vip_fragment;
    }

    @Override
    public void initData(boolean f) {
        initFragments();
        initTab();
        initUris();
        vp.setAdapter(new Viewpager_Adapter(getContext(), initUris()));
        indicatorCircle.setStartCount(initUris().size());
        indicatorCircle.setViewPager(vp);
        shopBaobuVp.setAdapter(new FragmentPagerAdapter(((BaseActivity) getContext()).getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return initFragments().get(position);
            }

            @Override
            public int getCount() {
                return initFragments().size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return initTab().get(position);
            }
        });
        shopTab.setupWithViewPager(shopBaobuVp);
        initTextViews();


        appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

                if (i == 0) {
                    swipeRefreshLayout.setEnabled(true);


                } else {

                    swipeRefreshLayout.setEnabled(false);

                }

            }
        });


    }

    private void initTextViews() {

        SpannableString spanString = new SpannableString("今日上线\n也可能不上新");
     ;
        StyleSpan span = new StyleSpan(Typeface.BOLD);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        Drawable d = getResources().getDrawable(R.drawable.b9h);
        d.setBounds(0, 0, 66, 66);
        RelativeSizeSpan absoluteSizeSpan=new RelativeSizeSpan(0.6f);



        spanString.setSpan(absoluteSizeSpan, 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                ImageSpan imageSpan = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);

        shopText01.setText(spanString);


        SpannableString spanString2= new SpannableString("圈子社区\n一起来玩呀");
    ;
        StyleSpan span2 = new StyleSpan(Typeface.BOLD);
        spanString.setSpan(span2, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        RelativeSizeSpan absoluteSizeSpan2=new RelativeSizeSpan(0.6f);



        spanString2.setSpan(absoluteSizeSpan, 4, spanString.length()-1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


        shopText02.setText(spanString2);


        SpannableString spanString3 = new SpannableString("人气排行\n因为你的热啊");
        LogUtil.e("spanStringspanString"+spanString.length());
        StyleSpan span3 = new StyleSpan(Typeface.BOLD);
        spanString3.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        RelativeSizeSpan absoluteSizeSpan3=new RelativeSizeSpan(0.6f);



        spanString3.setSpan(absoluteSizeSpan, 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


        shopText03.setText(spanString3);

    }


    @Override
    public boolean useCache() {
        return false;
    }

    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {


    }


    @Override
    public void onResume() {


        super.onResume();
    }
    @Override
    public void onHiddenChanged(boolean hidden) {



        super.onHiddenChanged(hidden);
    }

    @Override
    public void onRefresh() {
        LogUtil.e("onRefresh");
        super.onRefresh();
    }
}
