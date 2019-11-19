package com.example.momo.fragment;

import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.base.LazyFragment;

import butterknife.BindView;

public class ShopFragmentSomeThing extends LazyFragment {
    @BindView(R.id.shop_text)
    TextView shopText;

    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

    }

    @Override
    protected void loadData() {
        shopText.setText("商品");
    }

    @Override
    protected boolean enableRefush() {
        return false;
    }

    @Override
    public int getLayoutById() {
        return R.layout.fragment_shop_something;
    }

    @Override
    public void initData(boolean f) {

    }

    @Override
    public boolean useCache() {
        return false;
    }
}
