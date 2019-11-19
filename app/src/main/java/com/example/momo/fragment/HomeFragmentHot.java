package com.example.momo.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.base.LazyFragment;
import com.example.momo.utils.LogUtil;

public class HomeFragmentHot extends LazyFragment {
    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected boolean enableRefush() {
        return false;
    }

    @Override
    public int getLayoutById() {
        return R.layout.hoem_hot_layout;
    }

    @Override
    public void initData(boolean f) {

    }

    @Override
    public boolean useCache() {
        return false;
    }

    @Override
    public void onRefresh() {

        LogUtil.e("onRefresh-------热门---");
        super.onRefresh();
    }
}
