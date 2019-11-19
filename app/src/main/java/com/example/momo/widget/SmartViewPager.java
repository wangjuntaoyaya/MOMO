package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.momo.base.BaseActivity;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.LogUtil;

public class SmartViewPager extends ViewPager {
    private float down_x ,dowm_y;
    private float move_x,move_Y;
    private Context mcontext;

    public SmartViewPager(@NonNull Context context) {
        super(context);
    }

    public SmartViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext=context;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

                super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(CommonUtil.getScreenPix((BaseActivity) getContext())[0],heightMeasureSpec);
//      int h=
//      int status=CommonUtil.getStatusBarHeight(getContext());
//
//



    }

    boolean isComsume=false;
    @Override
    public boolean canScrollVertically(int direction) {
        super.canScrollVertically(direction);
              LogUtil.e("isComsume"+isComsume);
        return true;
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        super.canScrollHorizontally(direction);


       return true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        getParent().requestDisallowInterceptTouchEvent(true);
         switch (ev.getAction()){
             case MotionEvent.ACTION_DOWN :{
                down_x= ev.getX();
                dowm_y=ev.getY();
                 break;
             }
             case MotionEvent.ACTION_MOVE :{
                 move_x= ev.getX();
                 move_Y=ev.getY();
                 //当滑动12 就屏蔽掉父布局滚动事件
                 if((Math.abs(move_x-down_x)>Math.abs(move_Y-dowm_y))&&(Math.abs(move_x-down_x)>6)){


                     isComsume=true;

                 }




                 break;
             }
             case MotionEvent.ACTION_UP :{
                  LogUtil.e("ACTION_UPACTION_UPv");
                 isComsume=false;

                 break;
             }
         }


        return    super.onTouchEvent(ev);
    }


}
