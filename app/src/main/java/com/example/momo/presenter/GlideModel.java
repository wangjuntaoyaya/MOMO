package com.example.momo.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class GlideModel extends AppGlideModule {
    public GlideModel() {
        super();
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return super.isManifestParsingEnabled();
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {



        super.applyOptions(context, builder);

//        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        //设置磁盘缓存目录（和创建的缓存目录相同）
//        String downloadDirectoryPath = FileCacheUtil.getInstance().getPicCacheDir();
//        //设置缓存的大小为100M
//        int cacheSize = 200 * 1000 * 1000;
//        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize));

    }
}
//除了上面的配置还需要在配置清单中application节点内进行配置：
//
//<meta-data android:name="com.liuxin.glidedemo.utils.GlideCache"
//        android:value="GlideModule"/>
//