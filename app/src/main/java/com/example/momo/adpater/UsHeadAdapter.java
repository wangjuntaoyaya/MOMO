package com.example.momo.adpater;


import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class UsHeadAdapter <T>extends RecyclerView.Adapter<ViewHolder>  {


    protected Context context;
    protected List<T> mData;
    protected List<?> oTher;
    //布局类型
    protected SparseArray<Integer> types=new SparseArray<Integer>();
    protected int typesSize;
    protected int size;
    protected ViewHolder viewHolder;

  public void setDatas( List<T> d){

      typesSize+=8;
      mData=d;

  }
    public UsHeadAdapter(Context c, List<T> d ) {

        context = c;

        mData = d;
        size=d.size();

        typesSize=types.size();

    }
//    public UsHeadAdapter(Context c, List<?> d, SparseArray<Integer> sparseArray,String x) {
//
//        context = c;
//        size=d.size();
//        oTher = d;
//
//        types=sparseArray;
//        typesSize=types.size();
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        int layoutID=-1;

      layoutID=getLayoutId(viewType);
     viewHolder=   ViewHolder.get(context,null,parent,layoutID,-1);


        return viewHolder;

    }


    protected abstract int getLayoutId(int viewType);



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        viewHolder.updatePosition(position);
      convert(holder,position,mData.get(position));


    }

    protected abstract void convert(ViewHolder holder, int position, T mData);

    @Override
    public int getItemCount() {

        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {




                    return position;




    }








}