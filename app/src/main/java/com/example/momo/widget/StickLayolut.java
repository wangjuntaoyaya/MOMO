package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.customview.widget.ViewDragHelper;

import com.example.momo.utils.LogUtil;

public class StickLayolut extends LinearLayoutCompat {
    private ViewDragHelper v;

    public StickLayolut(@NonNull Context context) {
        super(context);
    }

    public StickLayolut(@NonNull Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        v=  ViewDragHelper.create(this,1.0f,new MyCallBack());
    }

    public StickLayolut(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);




    }




    class MyCallBack extends ViewDragHelper.Callback {




        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            LogUtil.e("tryCaptureView"+child.getClass().getName().toString());

            return true;        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            LogUtil.e("tryCaptureView"+left);
            LogUtil.e("tryCaptureView"+dx);
            return left;
        }


        @Override
        public int clampViewPositionVertical(View child, int top, int dy)
        {
            return top;
        }

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return     v.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        v.processTouchEvent(event);
        return true;
    }
    @Override
    public void computeScroll()
    {
        if(v.continueSettling(true))
        {
            invalidate();
        }
    }

}
