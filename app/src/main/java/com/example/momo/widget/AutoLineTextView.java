package com.example.momo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.momo.base.BaseActivity;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.LogUtil;

public class AutoLineTextView extends AppCompatTextView {
    private  int mixLine;
    private  int maxWidth;

    public AutoLineTextView(Context context) {
        super(context);
    }

    public AutoLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
     int w=   CommonUtil.getScreenPix((BaseActivity) getContext())[0];
      mixLine=w/7;
      maxWidth= (int) (1.0f*w/2.1);


    }

    public AutoLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        LogUtil.e("onDraw"+getWidth());

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
     int w  = CommonUtil.getScreenPix((BaseActivity) getContext())[0];

        super.onMeasure(MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST), heightMeasureSpec);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        TextPaint tp = new TextPaint((int) getTextSize());
        int w= (int) tp.measureText(text.toString());
        Rect rect=new Rect();
        tp.getTextBounds(text.toString(),0,text.toString().length(),rect);
    if(rect.right>mixLine){
        setLines(2);
     setEllipsize(TextUtils.TruncateAt.END);

    }else {
        setLines(1);
    }


        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    public void setLines(int lines) {
        TextPaint tp = new TextPaint((int) getTextSize());
     String content=  getText().toString();


       int w= (int) tp.measureText(content);


        super.setLines(lines);
    }
}
