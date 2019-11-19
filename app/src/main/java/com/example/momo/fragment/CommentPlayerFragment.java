package com.example.momo.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.base.LazyFragment;

public class CommentPlayerFragment extends LazyFragment {
    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

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
        return R.layout.fragment_comment_layout;
    }

    @Override
    public void initData(boolean f) {

    }

    @Override
    public boolean useCache() {
        return false;
    }
}
