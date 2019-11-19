package com.example.momo.manager;

import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.ViewUtils;

public class PopupwindowManager<T extends BaseActivity> {
    private T context;
    private PopupWindow popupWindow;
    private PopupMenu popupMenu;
    private View MenuLayout;

    public PopupwindowManager(T b) {


        context = b;

        initPopu();
        initPopuMune();


    }
    public void showPopMeue(){

        popupMenu.show();

    }
    private void initPopuMune() {

//        popupMenu.getMenuInflater().inflate(R.menu.sample_menu, popupMenu.getMenu());
//        popupMenu.show();
        View  view=  LayoutInflater.from(context).inflate(R.layout.item,null,false);
        popupMenu = new PopupMenu(context, view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popupMenu.setGravity(Gravity.BOTTOM);
        }


        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });

    }

    public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener callback) {

        popupMenu.setOnMenuItemClickListener(callback);
    }

    private void initPopu() {


        popupWindow = new PopupWindow(context);
        popupWindow.setHeight((CommonUtil.getScreenPix(context)[1])/2);
        popupWindow.setWidth((CommonUtil.getScreenPix(context)[0]));
         MenuLayout = ViewUtils.getInflaterView(context, R.layout.item, null);
        popupWindow.setContentView(MenuLayout);
        popupWindow.setOutsideTouchable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            popupWindow.setAttachedInDecor(true);
        }
        popupWindow.setElevation(2);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);

//        但是在android6.0以下还是无法点击外部取消Popupwindow. 可以通过设置背景来解决这个Bug
//
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());


    }
 private int PopLayoutId;

    private int getPopLayoutId(){
      return   PopLayoutId;

    }
    public  void  setPopupMenuLayoutId(int id) {
        PopLayoutId=id;
        initPopu();
    }
 public PopupWindow getPopWindow(){

        return popupWindow;
 }
    private int PopMenuLayoutId;
    private int getPopMeueLayoutId(){
        return   PopMenuLayoutId;

    }
    public  void  setPopMenuLayoutId(int id) {
        PopLayoutId=id;

    }
      public void locationPop(View parent,int x,int y){
          popupWindow.showAtLocation(parent,Gravity.NO_GRAVITY,x,y);
      }
    //跟随那个控件
    public void dropDownOfPopu(View v, int x, int y) {

        popupWindow.showAsDropDown(v, x, y);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

    }


    private void setAlphaForWindowBackgroud(float a) {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = a;
        context.getWindow().setAttributes(lp);


    }


}
