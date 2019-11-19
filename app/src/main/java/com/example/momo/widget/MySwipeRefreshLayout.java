package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    public MySwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

}
