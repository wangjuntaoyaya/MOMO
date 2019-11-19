package com.example.momo.widget;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.example.momo.utils.LogUtil;
import com.google.android.material.appbar.AppBarLayout;

public class MyBehavior extends CoordinatorLayout.Behavior<View> implements  View.OnScrollChangeListener{


    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        LogUtil.e("layoutDependsOn"+(child instanceof AppBarLayout));
        LogUtil.e("NestedScrollView"+(dependency instanceof NestedScrollView));
        LogUtil.e("LinearLayoutCompat"+(dependency instanceof LinearLayoutCompat));
        NestedScrollView n= ((NestedScrollView) dependency);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            n.setOnScrollChangeListener(this);
        }
//        child.offsetTopAndBottom(-child.getHeight());
        return dependency instanceof NestedScrollView;
    }
      int deltaY=0;
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {


//        child.setTranslationY(20);


//
//       int y= view.getScrollY();
//       int alpha=y/100;
//
//      child.setAlpha(alpha);
        LogUtil.e("onDependentViewChanged");
        return true;
    }

    int tempy=0;
    float oriScroll=0;
    float curScroll=0;
    boolean isUp=true;

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

        int maxY=530;
        float scrollY=1.0f*((NestedScrollView)target).getScrollY();


            if(dyUnconsumed>0&&isUp){
                oriScroll=oriScroll;
                isUp=false;
                LogUtil.e("maxY/dyConsumed>0");
            }else if(dyUnconsumed<0&&!isUp){
                oriScroll=scrollY;
                isUp=true;
                LogUtil.e("maxY/dyConsumed<0");
            }
//        LogUtil.e("onNestedScroll"+(child instanceof AppBarLayout)+((NestedScrollView)target).getScrollY()+(target instanceof NestedScrollView));


        if(0<=tempy&&tempy<=100){
            tempy+=dyConsumed;
        }
        if(tempy<0)tempy=100;
        if(tempy>100)tempy=0;
        LogUtil.e(tempy+"");

//        scrollY%150


        float s=scrollY-oriScroll;



        float alpha=1-1.0f*s/300;
        float rate=1.0f*scrollY_delta/600;
        child.setTranslationY((int) (-1.0f*rate*child.getHeight()));
//        child.offsetTopAndBottom();
        LogUtil.e(rate+"rate"+scrollY_delta);
//        child.setAlpha(1-1.0f*scrollY_delta/600);

        // 如果向下 滚动 getscrollY>200时候   用当前的scrolly-（scrolly—当前透明度60）40-0
        //如果向上滚动 getscrollY>200       scrolly  oriscrolly+100-当前透明度30


        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

     private boolean isShow(float a){
        if(a>=100)return true;
        return false;




     }

    @Override
    public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull MotionEvent ev) {

        return super.onTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onRestoreInstanceState(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull Parcelable state) {
        super.onRestoreInstanceState(parent, child, state);
    }

    @Nullable
    @Override
    public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout parent, @NonNull View child) {
        return super.onSaveInstanceState(parent, child);
    }
  private int scrollY_delta=0;
    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        scrollY_delta+=scrollY-oldScrollY;
        if(scrollY_delta>600){
            scrollY_delta=600;
        }
        if(scrollY_delta<0){
            scrollY_delta=0;
        }


    }
    //code 0向上 1向下
    private Boolean isHidden(View v,int code){
        if(v.getAlpha()==0&&code==0){
            return true;
        }
       if(v.getAlpha()>0||v.getAlpha()<1){


       }



        return false;
    }
}
