package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;

import com.example.momo.utils.LogUtil;

public class VideoPlayUi extends LinearLayout implements NestedScrollingParent2 {
    public VideoPlayUi(Context context) {
        super(context);
    }

    public VideoPlayUi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoPlayUi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VideoPlayUi(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        LogUtil.e("onStartNestedScroll");

        return false;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        LogUtil.e("onNestedScrollAccepted");
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        LogUtil.e("onStopNestedScroll");
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        LogUtil.e("onNestedScroll");
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        LogUtil.e("onNestedPreScroll");
    }
}
