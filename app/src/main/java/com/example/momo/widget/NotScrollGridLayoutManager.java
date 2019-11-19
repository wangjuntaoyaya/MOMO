package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

public class NotScrollGridLayoutManager extends GridLayoutManager {


    public NotScrollGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NotScrollGridLayoutManager(Context context, int spanCount) {

        super(context, spanCount);

    }

    public NotScrollGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollHorizontally() {

        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }
}
