package com.example.momo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.momo.utils.CommonUtil;

public class CircleIndicateLayout extends View  {
    private Paint lightPaint,grayPaint;
    private int mPadding=8;
    private int mSpace=12;

    public CircleIndicateLayout(Context context) {
        super(context);
    }

    public CircleIndicateLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleIndicateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public CircleIndicateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius= CommonUtil.dip2px(getContext(),16) /2-mPadding*2;
    }

    private int count;
    private int radius;
    private int currentIndex;
   private  void  init(){
       currentIndex=0;
       lightPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
       lightPaint.setColor(Color.WHITE);
       lightPaint.setStyle(Paint.Style.FILL);


        grayPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
       grayPaint.setColor(Color.GRAY);
       grayPaint.setStyle(Paint.Style.FILL);




    }
   public void  setStartCount(int c){
        count=c;


    }
    @Override
    protected void onDraw(Canvas canvas) {

       int w=getMeasuredWidth();
       int h=80;

       int start=mPadding+radius;
        Paint mpaint;
       for(int i=0;i<count;i++){

       if(i==currentIndex){
           mpaint=lightPaint;

       }else {

           mpaint=grayPaint;
       }
           canvas.drawCircle(start,mPadding*2+radius,radius,mpaint);

           start=start+radius*2+mSpace;
       }



        super.onDraw(canvas);
    }


    private ViewPager viewPager;
 public void setViewPager(ViewPager v){
     viewPager=v;

     viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         @Override
         public void onPageSelected(int position)
         {
             setCurrentItemPotion(position);
         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });


 }



    public void setCurrentItemPotion(int item) {


        currentIndex=item%count;
        invalidate();

    }
}
