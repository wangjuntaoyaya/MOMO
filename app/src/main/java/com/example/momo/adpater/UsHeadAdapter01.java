package com.example.momo.adpater;


import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class UsHeadAdapter01 <T>extends RecyclerView.Adapter<ViewHolder>  {


    protected Context context;
    protected List<T> mData;
    protected List<?> oTher;

    protected int typesSize;

    protected ViewHolder viewHolder;

    public void setDatas( List<T> d){


        mData=d;

    }
    public UsHeadAdapter01(Context c, List<T> d ) {

        context = c;

        mData = d;



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


        viewHolder=   ViewHolder.get(context,null,parent,getLayoutId(viewType),-1);


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