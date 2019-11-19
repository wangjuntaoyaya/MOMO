package com.example.momo.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momo.R;
import com.example.momo.utils.LogUtil;

import java.util.ArrayList;

public class RcAdapater extends RecyclerView.Adapter<VH> {
    private ArrayList<String> mData;
    private Context mContext;

    public RcAdapater(Context c){

        mContext=c;

    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        LogUtil.e((holder.text==null)+"'");
        holder.text.setText("dsadasdad");
    }




    @Override
    public int getItemCount() {
        return 50;
    }
}
  class VH extends RecyclerView.ViewHolder{
    TextView text;


      public VH(@NonNull View itemView) {

          super(itemView);
//          text=(TextView)itemView.findViewById(R.id.id_info);
      }
  }