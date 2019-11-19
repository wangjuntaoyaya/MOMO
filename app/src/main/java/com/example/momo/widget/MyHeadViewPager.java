package com.example.momo.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.momo.adpater.Viewpager_Adapter;

public class MyHeadViewPager extends ViewPager  {
    private  ItmeModel itmeModel;
    private Viewpager_Adapter viewpager_Adapter;

    public MyHeadViewPager(@NonNull Context context) {

        super(context);

    }

    public MyHeadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

         itmeModel=new ItmeModel();



    }



    boolean  isScroll=false;
    @Override
    public boolean canScrollHorizontally(int direction) {


        return true;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return false;
    }

    private int downX,downY;
    private int moveX,moveY;
    public boolean onTouchEvent(MotionEvent ev) {

     int a=  ev.getAction();
     switch (a){

         case MotionEvent.ACTION_DOWN:{


             downX= (int) ev.getX();
             break;

         }
         case MotionEvent.ACTION_MOVE:{

             moveX= (int) ev.getX();

             if(Math.abs(moveX-downX)>16){
                 isScroll=true;
                 handler.removeCallbacksAndMessages(null);
                 getParent().requestDisallowInterceptTouchEvent(true);

             }

               break;
         }
         case MotionEvent.ACTION_UP:{
             //当触摸到viewpager 但是滚动事件由recyclerview执行 导致没有收到move up事件
             isScroll=false;

             handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessageDelayed(0,2000);



             break;
         }
     }




        return super.onTouchEvent(ev);
    }



  public void ScrollNextPager(){
      viewpager_Adapter= (Viewpager_Adapter) getAdapter();

  }

    @Override
    public void setCurrentItem(int item) {

        super.setCurrentItem(item);
    }
    int i=0;

    Handler handler=  new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message msg) {
            if(isScroll){

                return;
            }

            switch (msg.what){

                case 0:{
                    //如果被销毁 i==0;
                   i= getCurrentItem();
                        i++;
                        setCurrentItem(i);


                    sendEmptyMessageDelayed(0,2000);


                    break;
                }


            }


        }
    };






    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {

        super.setOnScrollChangeListener(l);
    }


    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if(visibility==GONE){
            itmeModel.setCurrentItem(  i);
            setTag(getCurrentItem());

            handler.removeCallbacksAndMessages(null);

        }else {

            if(getTag()!=null){
                setCurrentItem(itmeModel.getCurrentItem());
            }

            isScroll=false;
            handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessageDelayed(0,2000);

        }
        super.onWindowVisibilityChanged(visibility);
    }


    interface  OnChangeItemPostion{

       void   setCurrentItemPotion(int item);

    }

private OnChangeItemPostion onChangeItemPostion;
    public void setOnChangeItemPostion(OnChangeItemPostion i){
        onChangeItemPostion=i;


    }



 class  ItmeModel{

        int index;
        public void setCurrentItem(int i){
            index=i;
    }

        public int getCurrentItem(){

            return index;
        }

    }


}
