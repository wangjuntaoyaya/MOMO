package com.example.momo.manager;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.utils.CommonUtil;
import com.example.momo.widget.MyFrameLayout;
import com.facebook.drawee.view.SimpleDraweeView;

public class ToolbarManger  {
    private androidx.appcompat.widget.Toolbar mToolbar;
    private BaseActivity mContext;

    private SimpleDraweeView user_image;
    private EditText ser_et;
    private ImageView one;
    private TextView center_tv;
    private ImageView two;
   private  int tb_height;
   private MyFrameLayout mContain;

    public ToolbarManger(androidx.appcompat.widget.Toolbar toolbar, BaseActivity b, MyFrameLayout contain){
        mContext=b;
        mToolbar=toolbar;
        mContain=contain;
        user_image= mToolbar.findViewById(R.id.user_image);
        ser_et= mToolbar.findViewById(R.id.search_edit);
        center_tv= mToolbar.findViewById(R.id.center_title);
        one= mToolbar.findViewById(R.id.one);
        two= mToolbar.findViewById(R.id.two);

        ViewTreeObserver o= mToolbar.getViewTreeObserver();

        ViewTreeObserver.OnGlobalLayoutListener oo= new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mToolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                tb_height=toolbar.getHeight();

                initLocation();
            }
        };

        o.addOnGlobalLayoutListener(oo);












    }



    public Toolbar build() {

             return null;
         }



    private void initLocation() {


        int[]  wh=CommonUtil.getScreenPix(mContext);
        int width=wh[0];
        int height=wh[1];

        //内容控件 高度适配
//        ViewGroup.LayoutParams cl = mContain.getLayoutParams();
//        LogUtil.e("CommonUtil.getStatusBarHeight(mContext)"+CommonUtil.getStatusBarHeight(mContext));
//        cl.height=height-tb_height*3-CommonUtil.getStatusBarHeight(mContext);
//        mContain.requestLayout();

       RelativeLayout.LayoutParams  up= (RelativeLayout.LayoutParams) user_image.getLayoutParams();

        up.width= CommonUtil.dip2px(mContext,40);
        up.height= CommonUtil.dip2px(mContext,40);

        user_image.requestLayout();
       user_image.setImageURI("https://ojlty2hua.qnssl.com/image-1570623035002-aWNfdXNlcm0ucG5n.png");


        RelativeLayout.LayoutParams  sp= (RelativeLayout.LayoutParams) ser_et.getLayoutParams();
        sp.width=width/2;
        sp.height=tb_height-tb_height/3;
        ser_et.requestLayout();


        RelativeLayout.LayoutParams  cp= (RelativeLayout.LayoutParams) center_tv.getLayoutParams();

//        cp.height=CommonUtil.dip2px(mContext,35);
//        cp.leftMargin= (int) (width/2.5);
        cp.leftMargin=width/2-mToolbar.getCurrentContentInsetLeft()-center_tv.getMeasuredWidth()/2;
        center_tv.requestLayout();

//
        RelativeLayout.LayoutParams  op= (RelativeLayout.LayoutParams) one.getLayoutParams();
        RelativeLayout.LayoutParams  tp= (RelativeLayout.LayoutParams) two.getLayoutParams();
        op.height=tp.height=tb_height-tb_height/2;
        op.width=tp.width=tb_height-tb_height/2;

        op.leftMargin= (int) (width-op.width*3.6);
        tp.leftMargin= (int) (width-op.width*1.8);
        one.setImageResource(R.drawable.ic_archive_black_24dp);
        one.requestLayout();
        two.requestLayout();
        two.setImageResource(R.drawable.ic_message_64dp);



       center_tv.setVisibility(View.GONE);
//        two.setVisibility(View.GONE);
//      one.setVisibility(View.GONE);


    }

//1 2  3 4 代表四个页面
    public void  setRightIcon(int i){

       switch (i){
           case 1:{
               ser_et.setVisibility(View.VISIBLE);
               center_tv.setVisibility(View.GONE);
               one.setVisibility(View.VISIBLE);
               two.setVisibility(View.VISIBLE);
               one.setImageResource(R.drawable.ic_archive_black_24dp);
               two.setImageResource(R.drawable.ic_message_64dp);
               break;

           }
           case 2:{
               center_tv.setText("频道");
               center_tv.setVisibility(View.VISIBLE);
                ser_et.setVisibility(View.GONE);
               one.setVisibility(View.GONE);
               two.setVisibility(View.GONE);
               break;
           }
           case 3:{
               center_tv.setVisibility(View.VISIBLE);
               ser_et.setVisibility(View.GONE);
               one.setVisibility(View.GONE);
               center_tv.setText("动态");

               two.setImageResource(R.drawable.ic_download_edit);
               two.setVisibility(View.VISIBLE);

               break;
           }
           case 4:{

               ser_et.setVisibility(View.GONE);
               center_tv.setText("会员购");
               center_tv.setVisibility(View.VISIBLE);
               one.setVisibility(View.VISIBLE);
               two.setVisibility(View.VISIBLE);
               one.setImageResource(R.drawable.shop);
               two.setImageResource(R.drawable.ic_whatshot_black_24dp);
               break;

           }



       }




    }
    public void setCenterTitle(String s){
        center_tv.setText(s);






    }

}
