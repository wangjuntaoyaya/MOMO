package com.example.momo.adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momo.R;
import com.example.momo.activitys.HomeActivity;
import com.example.momo.activitys.PlayVedioActivity;
import com.example.momo.base.BaseActivity;
import com.example.momo.base.LazyFragment;
import com.example.momo.dao.entity.RecomendsInfo;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.DateUtil;
import com.example.momo.utils.ImageUtil;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.AutoLineTextView;
import com.example.momo.widget.CircleIndicateLayout;
import com.example.momo.widget.MyHeadViewPager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RcHeadAdapter extends RecyclerView.Adapter {

    private LazyFragment lazyFragment;
    private Context context;
    private List<RecomendsInfo.ResultBean.UsersBean> mData;
    private ArrayList<String> banders;



    public RcHeadAdapter(Context c, List<RecomendsInfo.ResultBean.UsersBean> d, LazyFragment f, ArrayList<String> b) {
        LogUtil.e("RcHeadAdapter+++");
        context = c;

        mData = d;
        lazyFragment=f;

        banders=b;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        if (viewType == 0) {

            v = LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false);


            return new HeadVh(v);
        }
        v = LayoutInflater.from(context).inflate(R.layout.item_player_view, parent, false);
        ButterKnife.bind(v);


        return new ContentVh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (getItemViewType(position) == 0) {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(R.drawable.ic_02);
            arrayList.add(R.drawable.ic_03);
            arrayList.add(R.drawable.ic_04);
            arrayList.add(R.drawable.ic_05);
            arrayList.add(R.drawable.ic_02);
            ImageUtil.adapterHeight(context, banders.get(0), CommonUtil.getScreenPix((BaseActivity) context)[0], new ImageUtil.OnLoaddImageResultListener() {
                @Override
                public void getHeight(int h) {
                    ((HeadVh) holder).viewPager.getLayoutParams().height=h;
                }
            });


            ((HeadVh) holder).viewPager.setAdapter(new Viewpager_Adapter(context, banders));

            ((HeadVh) holder).circlePageIndicator.setStartCount(banders.size());
            ((HeadVh) holder).circlePageIndicator.setViewPager(((HeadVh) holder).viewPager);


        } else {


            RecomendsInfo.ResultBean.UsersBean data = mData.get(position);

            ContentVh h = ((ContentVh) holder);
            Uri ui = Uri.parse(data.getImage());
            Uri vi = Uri.parse(data.getThumbnail());


            String strDateFormat = "yyyy-MM-dd HH:mm:ss";

            Date mdate = DateUtil.stringToDate(data.getTime(), strDateFormat);
            String time = "0";
            if (mdate != null) {
                time = DateUtil.getDescriptionTimeFromTimestamp(DateUtil.dateToLong(mdate));

            }

            h.videoTime.setText(data.getViewtime());

            h.title.setText(data.getTitle());

            h.userImage.setImageURI(ui);
            h.videoImage.setImageURI(vi);
            h.videoDanmu.setText(data.getDanmu());
            h.videoTag.setText(data.getTag());
            h.videoWatch.setText(data.getWatch());
            h.viewusername.setText(data.getName());
            h.viewPublishTime.setText(time);

            h.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayVedioActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("link", data.getUrl());
                    bundle.putSerializable("2",data);
                    intent.putExtras(bundle);
                    ((HomeActivity) context).startActivity(intent);
                    ((HomeActivity) context).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                }
            });


        }


    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {


        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {


        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {

            return 0;

        }

        return 1;
    }


    class ContentVh extends RecyclerView.ViewHolder {
        @BindView(R.id.view_user_name)
        AppCompatTextView viewusername;
        @BindView(R.id.video_time)
        AppCompatTextView videoTime;
        @BindView(R.id.video_watch)
        AppCompatTextView videoWatch;
        @BindView(R.id.video_danmu)
        AppCompatTextView videoDanmu;
        @BindView(R.id.play_view_my)
        RelativeLayout playViewMy;
        @BindView(R.id.user_image)
        SimpleDraweeView userImage;
        @BindView(R.id.title)
        AutoLineTextView title;
        @BindView(R.id.view_publish_time)
        AppCompatTextView viewPublishTime;
        @BindView(R.id.video_tag)
        Button videoTag;
        @BindView(R.id.video_more)
        ImageView videoMore;
        @BindView(R.id.linearLayout)
        RelativeLayout linearLayout;
        @BindView(R.id.video_image)
        SimpleDraweeView videoImage;
        @BindView(R.id.plaer_item_layout)
        LinearLayout layout;

        public ContentVh(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeadVh extends RecyclerView.ViewHolder {
        MyHeadViewPager viewPager;
        CircleIndicateLayout circlePageIndicator;

        public HeadVh(@NonNull View itemView) {

            super(itemView);

            viewPager = itemView.findViewById(R.id.vp);


            circlePageIndicator= itemView.findViewById(R.id.indicator_circle);

        }


}



}