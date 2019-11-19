package com.example.momo.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Integer> data;

    public ChannelAdapter(Context c, ArrayList<Integer> d) {

        context = c;
        data = d;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_chanel, parent, false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ( (ViewHolder)holder).chanelText.setText(context.getResources().getStringArray(R.array.chanel_name)[position]);
        ( (ViewHolder)holder).channelIcon.setBackgroundResource((data.get(position)));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.channel_icon)
        SimpleDraweeView channelIcon;
        @BindView(R.id.chanel_text)
        TextView chanelText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}



