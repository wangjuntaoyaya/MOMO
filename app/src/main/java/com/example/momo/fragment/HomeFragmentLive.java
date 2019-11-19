package com.example.momo.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.base.LazyFragment;
import com.example.momo.utils.LogUtil;

public class HomeFragmentLive  extends LazyFragment {
    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

    }

    @Override
    protected void loadData() {
        LogUtil.e("HomeFragmentLiveHomeFragmentLive->>loadData");
    }

    @Override
    protected boolean enableRefush() {
        return false;
    }

    @Override
    public int getLayoutById() {
        return R.layout.hoe_live_layout;
    }

    @Override
    public void initData(boolean f) {

    }

    @Override
    public boolean useCache() {
        return false;
    }




}
