package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.momo.utils.LogUtil;

public class MyCoorateLayout extends CoordinatorLayout {
    public MyCoorateLayout(@NonNull Context context) {

        super(context);
    }

    public MyCoorateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCoorateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onStopNestedScroll(View target) {

        super.onStopNestedScroll(target);

    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        LogUtil.e("target"+target.getClass().getName().toString());
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        LogUtil.e("target"+target.getClass().getName().toString());
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {


        LogUtil.e("target"+getParent().getClass().getName().toString());
        super.onNestedPreScroll(target, dx, dy, consumed, type);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
