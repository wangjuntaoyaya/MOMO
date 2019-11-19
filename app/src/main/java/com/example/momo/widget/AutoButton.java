package com.example.momo.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.example.momo.R;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class AutoButton  extends AppCompatButton {
    private  int color;
    private Paint paint;
    private int textSize;
    private Rect mbound;
    private int textWidth;
    private int textHeight;
    private Rect mNum;
    private int num_textWidth,num_textHeight;

    public AutoButton(Context context) {
        super(context);

    }

    public AutoButton(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoButton);
        color=typedArray.getColor(R.styleable.AutoButton_num_color,Color.BLACK);
        int size=typedArray.getInteger(R.styleable.AutoButton_num_size,12);

        textSize= CommonUtil.sp2px(context,size);

  init();
    }



    private void init() {
          mbound=new Rect();
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
//        paint.setTypeface(Typeface.DEFAULT_BOLD);

        paint.setTextSize(textSize);
        paint.setColor(color);
           paint.setStrokeJoin(Paint.Join.ROUND);

    }
    private  String  default_num="3";
    public void setTextNum(){


    }
    private void measureText(String text) {
        paint.getTextBounds(text, 0, text.length(), mbound);
        textWidth = (int) paint.measureText(text);
        Paint.FontMetrics fm = paint.getFontMetrics();
        textHeight = (int) (fm.descent + fm.ascent);

    }
    private void measureTextWithNum(String text) {
        paint.getTextBounds(text, 0, text.length(), mNum);
        num_textWidth = (int) paint.measureText(text);
        Paint.FontMetrics fm = paint.getFontMetrics();
        num_textHeight = (int) (fm.descent + fm.ascent);

    }


    public AutoButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }





    @Override
    protected void onDraw(Canvas canvas) {


//
        measureText(getText().toString());
        // 距离为字体的最左边到 容器的最左边距离 加了默认间隔一半 不贴边
        int  move=getWidth()/2-mbound.right/2-mbound.right/3
                ;



        canvas.translate(-move,0);

        measureText(default_num);
       canvas.drawText(default_num,getWidth()-mbound.right-mbound.right/2,getHeight()/2-textHeight/2,paint);

        super.onDraw(canvas);
    }

    int numb = 3;
    private void postTimer(){

      Timer timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!isTimer) return;
                if(numb==0){
                    timer.cancel();
                    if(CallBack!=null){
                        CallBack.isFinish(true);

                        return;

                    }

                }else {
                    numb--;
                }

            LogUtil.e("numb"+numb);
                default_num=""+numb;
                postInvalidate();
            }
        },1000,1000);
    }



    private  OnTimerNotify CallBack=null;
  public void setOnTimerNotify(OnTimerNotify t){

      CallBack=t;

  }

  interface  OnTimerNotify{

       void isFinish(Boolean b);


  }

  public  void start(){
      isTimer=true;

      postTimer();
  }

  boolean isTimer=true;
  public void Stop(){

      isTimer=false;


  }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth()-getMeasuredWidth()/10,getMeasuredHeight()-getMeasuredHeight()/8);
    }
}
