package com.example.momo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class MessageView extends AppCompatTextView {
    private Paint paint;
    private Paint mPaint;

    public MessageView(Context context) {
        super(context);
    }

    public MessageView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public MessageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        super.onMeasure( MeasureSpec.makeMeasureSpec(getMeasuredHeight(),MeasureSpec.EXACTLY), heightMeasureSpec);
    }

    private void init() {

        paint=  new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#F14584"));

        mPaint=  new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(3);







    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),30,30,paint);
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),30,30,mPaint);

        super.onDraw(canvas);
    }
}
