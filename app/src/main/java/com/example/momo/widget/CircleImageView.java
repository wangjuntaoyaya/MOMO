package com.example.momo.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.momo.utils.LogUtil;


public class CircleImageView extends AppCompatImageView {

    private static final String TAG = null;
    private Paint mPaint;
    private Bitmap bitmap;
    private BitmapDrawable bd;

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(0xff0000ff);
        // OverScroller
    }

    private void drawBitmap(Bitmap src) {
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas();

        c.setBitmap(bitmap);
        c.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,mPaint);
        // Dst 圆矩形
//        c.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), 90, 90,
//                mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // src
        c.drawBitmap(src, 0, 0, mPaint);
        invalidate();

    }
 
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);



    }

    public  void  goCircleBmap(){
//        bd = (BitmapDrawable) getDrawable();
        Bitmap mBitmap = mbtimap;
        if(mbtimap==null) return;
        float rate = Math.max(getWidth() * 1.0f / mBitmap.getWidth(),
                getWidth() * 1.0f / mBitmap.getHeight());
        // mBitmap.setWidth((int) (rate*mBitmap.getWidth())); 不能直接设置
        // mBitmap.setHeight((int) (rate*mBitmap.getHeight()));
        // 缩小图片
        mBitmap = Bitmap.createScaledBitmap(mBitmap,
                (int) (rate * mBitmap.getWidth()),
                (int) (rate * mBitmap.getHeight()), true);

        drawBitmap(mBitmap);


    }

    public   Bitmap mbtimap;
   public void setBitmap(Bitmap b){

       mbtimap=b;
       goCircleBmap();


   }

    @Override
    protected void onDraw(Canvas canvas) {
       if(bitmap==null) {

           super.onDraw(canvas);
           return;
       }


        canvas.drawBitmap(bitmap, 0, 0, null);

    }

}


