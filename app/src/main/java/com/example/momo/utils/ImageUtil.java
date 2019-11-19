package com.example.momo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtil {

    public static int[] getImgWH(String urls) throws Exception {

        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();
        Bitmap image = BitmapFactory.decodeStream(is);

        int srcWidth = image.getWidth();      // 源图宽度
        int srcHeight = image.getHeight();    // 源图高度
        int[] imgSize = new int[2];
        imgSize[0] = srcWidth;
        imgSize[1] = srcHeight;

        //释放资源
        image.recycle();
        is.close();
        conn.disconnect();

        return imgSize;

    }
    public static void   adapterHeight(Context context,String urls,int width,OnLoaddImageResultListener onLoaddImageResultListener){



        Glide.with(context)
                .load(urls).apply(RequestOptions.bitmapTransform(new RoundedCorners(1)))
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {


                        if(onLoaddImageResultListener!=null){
                            LogUtil.e("xiaoya"+(int) (width*(1.0f*resource.getIntrinsicHeight()/resource.getIntrinsicWidth())));
                            onLoaddImageResultListener.getHeight((int) (width*(1.0f*resource.getIntrinsicHeight()/resource.getIntrinsicWidth())));
                        }

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


    }


    public interface OnLoaddImageResultListener{
        void getHeight(int h);


    }

}
