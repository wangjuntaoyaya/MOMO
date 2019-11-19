package com.example.momo.adpater;

import android.content.Context;
import android.util.SparseArray;

import com.example.momo.R;
import com.example.momo.dao.entity.ImageInfo;

import java.util.List;

import rx.functions.Action1;

public  class ImageAdapter extends UsHeadAdapter<ImageInfo> implements Action1<List<ImageInfo>> {


    public ImageAdapter(Context c, List<ImageInfo> d, SparseArray<Integer> sparseArray) {
        super(c, d);
    }




    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_baobuliu_layout;
    }

    @Override
    protected void convert(ViewHolder holder, int position, ImageInfo mData) {

        holder.setImageUrlGlid(R.id._bao_image, 2,mData);

    }
  private boolean down=true;
    public  void setRefreshState(boolean b){
        down=b;


    }
    @Override
    public void call(List<ImageInfo> ImageInfo) {




//        mData.addAll(index,ImageInfo);

    notifyItemRangeChanged(mData.size()-8,mData.size());

    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}
