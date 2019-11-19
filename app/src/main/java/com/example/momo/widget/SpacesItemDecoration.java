package com.example.momo.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.utils.CommonUtil;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int space_grid;
    private boolean isGrid;
    private    boolean isHorizontal=false;

    public  SpacesItemDecoration(){

    };
    public SpacesItemDecoration(int space,boolean h) {
        isHorizontal=h;
        this.space =  space;
        isGrid=false;
    }
    public  SpacesItemDecoration(Context context , boolean y  ) {

       int w=  CommonUtil.getScreenPix((BaseActivity) context)[0];
       int imageWidth= w/2- CommonUtil.dip2px(context,context.getResources().getDimension(R.dimen.space));
       int s=(w-CommonUtil.dip2px(context,context.getResources().getDimension(R.dimen.space))-imageWidth*2)/2;
        this.space_grid = s;
        isGrid=y;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {


      if(isGrid){
          outRect.left = space_grid;

      }else{

          outRect.left = space;
      }

        if(isHorizontal){
            outRect.right = space/2;
        }else {
            outRect.right = space;
        }

        outRect.bottom = space;


        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = 0;

        outRect.top=20;
    }

}