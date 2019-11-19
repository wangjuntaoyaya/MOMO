package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class NotScrollLinearLayoutManager extends StaggeredGridLayoutManager {


    public NotScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NotScrollLinearLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
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


