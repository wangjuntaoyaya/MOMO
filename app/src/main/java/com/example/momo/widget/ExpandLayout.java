package com.example.momo.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.momo.R;
import com.example.momo.utils.LogUtil;

public class ExpandLayout  extends LinearLayout {
    private LinearLayout linearLayout;

    public ExpandLayout(Context context) {
        super(context);
    }

    public ExpandLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);



    }

    public ExpandLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExpandLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

  private TextView textView;


    private ImageView imageView;
   private int LinearLayout_height;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
  private boolean isMeasureHeightOK=false;
    private int singleLineHeight=0;
    private int  textViewHeight=0;
    private int line=0;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(!isMeasureHeightOK){
            isMeasureHeightOK=true;
            LinearLayout_height=linearLayout.getMeasuredHeight();
           String  title= textView.getText().toString();
            textViewHeight=textView.getMeasuredHeight();
          singleLineHeight= textViewHeight/textView.getLineCount();
          line= textView.getLineCount();
// 初始化折叠状态
            toggle();
        }

 LogUtil.e("onSizeChanged");

    }

   private int  measurTextsinHeight(String title){
       TextPaint tp = new TextPaint((int) textView.getTextSize());
        Rect rect=new Rect();
        tp.getTextBounds(title,0,2,rect);

        return rect.bottom;

    }

    @Override
    protected void onFinishInflate() {
        View view =  getChildAt(0);

        textView= view.findViewById(R.id.viedo_expand_title_textView);
//        linearLayout= view.findViewById(R.id.expand_introduce_layout);
        linearLayout=this.findViewById(R.id.expand_introduce_layout);


        imageView=view.findViewById(R.id.viedo_toggle);
        imageView.setImageResource(R.drawable.ic_open_arrow_up);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                toggle();

            }
        });
        super.onFinishInflate();

    }

  private boolean isOpen=true;
    public void toggle(){


        if(!isOpen){

            isOpen=true;


        }else {
            isOpen=false;


        }

        expand();


    }
    private  void expand(){
        linearLayout.clearAnimation();

        linearLayout.setAnimation(  new Animation() {
          private   int mheight;
            private  LinearLayout.LayoutParams layoutParams;
            private  LinearLayout.LayoutParams textview_params;
            @Override
            public void initialize(int width, int height, int parentWidth, int parentHeight) {

                setDuration(200);
                setInterpolator(new DecelerateInterpolator());
              textview_params= (LayoutParams) textView.getLayoutParams();
                 layoutParams= (LayoutParams) linearLayout.getLayoutParams();
                super.initialize(width, height, parentWidth, parentHeight);


            }

            @Override
            public void setAnimationListener(AnimationListener listener) {
                super.setAnimationListener(listener);
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime==1){
                    if(!isOpen){
                        textView.setLines(1);
                        textView.setEllipsize(TextUtils.TruncateAt.END);

                    }


                }
               if(!isOpen){
                   layoutParams.height= (int) (LinearLayout_height-interpolatedTime*LinearLayout_height);
                     imageView.setRotation(180*interpolatedTime);
                   textview_params.height=(int) ((textViewHeight)-interpolatedTime*(textViewHeight-singleLineHeight));
                   textView.requestLayout();
                   linearLayout.requestLayout();



               }else {
                   if(interpolatedTime==0){
                       textView.setLines(line);
                   }
                   LogUtil.e("initialize-----------------------"+singleLineHeight);
                   imageView.setRotation(180+(180*interpolatedTime));
                   textview_params.height=singleLineHeight+(int) (interpolatedTime*(textViewHeight-singleLineHeight));
                   textView.requestLayout();
                   layoutParams.height= (int) (interpolatedTime*LinearLayout_height);
                   linearLayout.requestLayout();




               }



            }


        });



    }
    private void open() {
    }

}
