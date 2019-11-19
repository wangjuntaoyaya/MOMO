package com.example.momo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessageTipView extends FrameLayout {
    private  TextView textView;
    private Paint paint;

    public MessageTipView(@NonNull Context context) {
        super(context);
    }

    public MessageTipView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
         textView=    new TextView(context);


        init();
    }


    public MessageTipView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MessageTipView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {

         paint=  new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);




    }
private ImageView imageView;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageView= (ImageView) getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    private boolean first=true;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        if(first){
            first=false;
            imageView.getMeasuredHeight();
            imageView.getMaxWidth();


        }
    }

    @Override
    protected void onDraw(Canvas canvas) {



        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        canvas.drawRoundRect(0,0,200,200,20,20,paint);
        super.dispatchDraw(canvas);
    }
}
