package com.example.momo.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.base.LazyFragment;

public class MessageFragment extends LazyFragment {
    @Override
    protected void loadData() {

    }

    @Override
    protected boolean enableRefush() {
        return false;
    }

    @Override
    public int getLayoutById() {
        return R.layout.message_layout;
    }

    @Override
    public void initData(boolean f) {

    }

    @Override
    public boolean useCache() {
        return false;
    }
    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

    }

}
