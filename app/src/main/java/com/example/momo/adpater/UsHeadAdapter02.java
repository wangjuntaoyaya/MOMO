package com.example.momo.adpater;



import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momo.utils.LogUtil;


public abstract class UsHeadAdapter02 <T>extends RecyclerView.Adapter<ViewHolder> {


    private Context context;


    //布局类型
    private SparseArray<Integer> types=new SparseArray<Integer>();
    private int typesSize;
  private int size;
    private ViewHolder viewHolder;


    public UsHeadAdapter02(Context c,int s,SparseArray<Integer> sparseArray) {

        context = c;

        size = s;

        types=sparseArray;
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




     LogUtil.e("onCreateViewHolder"+types.get(viewType));
        // 0或者其他对应不一样的布局  第一个布局  1 就是常规重复局  布局类型==key值
        viewHolder=   ViewHolder.get(context,null,parent,types.get(viewType),-1);


        return viewHolder;

    }






    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogUtil.e("position"+position);

        viewHolder.updatePosition(position);
        convert(holder,position);


    }

    protected abstract void convert(ViewHolder holder, int position);

    @Override
    public int getItemCount() {

        return size;
    }

    @Override
    public int getItemViewType(int position) {

        if (typesSize != 0) {
            for (int i = 0; i < typesSize; i++) {

                if (position == types.keyAt(i)) {


                    return position;
                }

            }

        }


        return 1;
    }








}