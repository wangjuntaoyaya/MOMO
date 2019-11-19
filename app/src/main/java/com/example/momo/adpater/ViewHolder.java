package com.example.momo.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.momo.R;
import com.example.momo.activitys.HomeActivity;
import com.example.momo.activitys.PhotoActivity;
import com.example.momo.adpater.listener.OnItemCheckListener;
import com.example.momo.base.BaseActivity;
import com.example.momo.dao.entity.ImageInfo;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.AutoImageView;
import com.example.momo.widget.CircleIndicateLayout;
import com.example.momo.widget.MyHeadViewPager;
import com.example.momo.widget.NotScrollGridLayoutManager;
import com.example.momo.widget.SpacesItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;
    private int mLayoutId;
  public void setmConvertView(int layoutId){

       mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parentView,

              false);

  }
    public ViewHolder(Context context, View itemView, ViewGroup parent, int position) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mPosition = position;
        mViews = new SparseArray<View>();
        mConvertView.setTag(this);
        parentView=parent;

    }
private static int fixWidth;
private ViewGroup parentView;
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {


        fixWidth= (int) (CommonUtil.getScreenPix(((BaseActivity) context))[0]/2-context.getResources().getDimension(R.dimen.space));
        if (convertView == null) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent,

                    false);

            ViewHolder holder = new ViewHolder(context, itemView, parent, position);
            holder.mLayoutId = layoutId;
            return holder;
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        LogUtil.e("setText" + tv.getText());
        return this;
    }

    public ViewHolder setImageUrl(int viewId, String uri) {
        SimpleDraweeView view = getView(viewId);
        view.setImageURI(Uri.parse(uri));

        return this;
    }


    /*
    *  固定模板
    * */
    public ViewHolder setImageUrlGlid(int viewId, String uri,int count) {
        int space= CommonUtil.dip2px(mContext,mContext.getResources().getDimension(R.dimen.space));
        ImageView view = getView(viewId);
        if(view.getParent() instanceof  FrameLayout){
            ((FrameLayout) view.getParent()).getLayoutParams().width = CommonUtil.getScreenPix((BaseActivity) view.getContext())[0] / count-space;
            ((FrameLayout) view.getParent()).getLayoutParams().height = (int) (((FrameLayout) view.getParent()).getLayoutParams().width/1.5);
        }

        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//                Glide.with(mContext)
//                .load(uri).apply(new RequestOptions().bitmapTransform(new RoundedCorners(12)))
//                .into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//
//
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });
        Glide.with(mContext)
                .load(uri).apply(RequestOptions.bitmapTransform(new RoundedCorners(12)))
                .into(view);
        return this;
    }

    public ViewHolder setImageUrlGlid(int viewId, int count, ImageInfo imageInfo) {

        int space= CommonUtil.dip2px(mContext,mContext.getResources().getDimension(R.dimen.space));
        AutoImageView view = getView(viewId);
        view.getLayoutParams().width=fixWidth;
        view.getLayoutParams().height= (int) (fixWidth*((1.0f*imageInfo.getHegiht())/imageInfo.getWidth()));
//        view.setOriHeight(imageInfo.getHegiht());
//        view.setOriWidth(imageInfo.getWidth());

    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
      Glide glide=Glide.get(mContext);
        GlideBuilder glideBuilder=new GlideBuilder()
                .setDiskCache(new InternalCacheDiskCacheFactory(mContext, 1024*1014*10));
        glide.setMemoryCategory(MemoryCategory.HIGH);

        glide.with(mContext)
                .load(imageInfo.getUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(12))).placeholder(R.drawable.bh_).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, PhotoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",imageInfo.getUrl());
                intent.putExtras(bundle);

                ActivityOptionsCompat compat=   ActivityOptionsCompat.makeSceneTransitionAnimation((HomeActivity)mContext,view,"pic");
                ActivityCompat.startActivity(mContext, intent, compat.toBundle());
            }
        });



        return this;


    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setButtonText(int viewId, String string) {
        Button view = getView(viewId);
        view.setText(string);
        return this;
    }

    public ViewHolder setRadioGraupButtonText(int viewId, String string) {
        RadioButton view = getView(viewId);
        view.setText(string);
        return this;
    }
    public ViewHolder setCheckBoxLayoutText(int viewParent , ArrayList<Integer> chiles,ArrayList<String> data, OnItemCheckListener onItemCheckListener) {
       int position;

        LinearLayout view = getView(viewParent);


     int count=   view.getChildCount();
        for(int i=0;i<count;i++){

            CheckBox checkBox=getView(chiles.get(i));
            checkBox.setText((data.get(i)));
            checkBox.setTag(i);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(onItemCheckListener!=null){
                     onItemCheckListener.cheakState(isChecked, (Integer) buttonView.getTag());

                 }

                }
            });

        }


        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public ViewHolder setOnClickListener(int viewId,

                                         View.OnClickListener listener) {

        View view = getView(viewId);
        if(view==null){

        }else {
            view.setOnClickListener(listener);
        }

        return this;
    }

    public ViewHolder setOnTouchListener(int viewId,
                                         View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId,
                                             View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public ViewHolder setNvigationListener(int viewId,
                                           BottomNavigationView.OnNavigationItemSelectedListener listener) {
        BottomNavigationView view = getView(viewId);
        view.setOnNavigationItemSelectedListener(listener);
        return this;
    }

    public int getLayoutId() {
        return mLayoutId;
    }
//动态设置图片比例和显示
    public ViewHolder setViewPagerAdapter(int h,int indicatorId,int mLayoutId,int size,String url, PagerAdapter p) {
        MyHeadViewPager viewPager = getView(mLayoutId);
        viewPager.getLayoutParams().height=h;


        viewPager.setPageMargin((int) mContext.getResources().getDimension(R.dimen.space));
        CircleIndicateLayout circlePageIndicator= getView(indicatorId);
      circlePageIndicator.setStartCount(size);
       circlePageIndicator.setViewPager(viewPager);

        viewPager.setAdapter(p);


        return this;

    }

    public ViewHolder setRecyclerViewAdapter(int viewId, boolean horizontal,boolean isGrid, int gridCount, RecyclerView.Adapter<ViewHolder> h) {

        RecyclerView view = getView(viewId);
        view.setNestedScrollingEnabled(false);
        if (!isGrid) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            if (horizontal) {
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

            } else {
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

            }
            view.setLayoutManager(linearLayoutManager);

            view.addItemDecoration(new SpacesItemDecoration( CommonUtil.dip2px(mContext, mContext.getResources().getDimension(R.dimen.space)),true));
        } else {
            NotScrollGridLayoutManager gridLayoutManager = new NotScrollGridLayoutManager(mContext, gridCount);


            view.setLayoutManager(gridLayoutManager);

            view.addItemDecoration(new SpacesItemDecoration(mContext,true));
        }

//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.recycle_decoration_divider));
//
//        view.addItemDecoration(dividerItemDecoration);
        view.setAdapter(h);
        return this;
    }

    public void updatePosition(int position) {
        mPosition = position;
    }

    public ViewHolder  setFlowlayout(int id_flowlayout,TagAdapter tagAdapter,TagFlowLayout.OnTagClickListener onTagClickListener) {
        TagFlowLayout flowLayout= getView(id_flowlayout);
if(onTagClickListener!=null){
    flowLayout.setOnTagClickListener(onTagClickListener);
}

        flowLayout.setAdapter(tagAdapter);


        return  this;
    }
}
