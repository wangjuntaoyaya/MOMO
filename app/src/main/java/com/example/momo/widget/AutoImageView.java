package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.utils.CommonUtil;

public class AutoImageView extends AppCompatImageView {
    private  int  fixWidth=0;
    public int getOriWidth() {
        return oriWidth;
    }

    public void setOriWidth(int oriWidth) {
        this.oriWidth = oriWidth;
    }

    public int getOriHeight() {
        return oriHeight;
    }

    public void setOriHeight(int oriHeight) {
        this.oriHeight = oriHeight;
    }

    private int oriWidth=0;
    private int oriHeight=0;




    public AutoImageView(Context context) {
        super(context);
    }

    public AutoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public AutoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w=fixWidth;
        int h=0;

        if(oriHeight>0&&oriWidth>0){

           h= (int) (fixWidth *((1.0f*oriHeight/oriWidth)));

        }

          super.onMeasure(widthMeasureSpec,heightMeasureSpec);
//        setMeasuredDimension(w,h);
    }
}
