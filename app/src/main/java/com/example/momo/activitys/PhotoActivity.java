package com.example.momo.activitys;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoActivity extends BaseActivity {

//    @BindView(R.id.photoView)
//    ImageView photoView;
    @BindView(R.id.photoView01)
PhotoView photoView01;
    private Bitmap bitmap;

    @Override
    public void initViews(Bundle savedInstanceState) {

        ViewCompat.setTransitionName(photoView01,"pic");
       String url= getIntent().getExtras().getString("url");

//        Glide.with(this)
//                .load(url).apply(RequestOptions.bitmapTransform(new RoundedCorners(12)))
//                .into(photoView);
        Glide.with(this).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
               bitmap= resource;
                photoView01.setImageBitmap(resource);

            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });







    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    @Override
    protected void onDestroy() {
//        photoView01.setVisibility(View.GONE);
//        photoView.setVisibility(View.VISIBLE);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {



        super.onBackPressed();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.layout_photo_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
