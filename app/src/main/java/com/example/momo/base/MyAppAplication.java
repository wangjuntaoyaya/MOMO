package com.example.momo.base;


import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.Looper;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.momo.constants.ConstantFile;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.PlatformConfig;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class
MyAppAplication extends Application {



    public static MyAppAplication mInstance;
    public SharedPreferences sp;
    private CompositeDisposable compositeDisposable;


    public void onCreate() {
         compositeDisposable = new CompositeDisposable();
        sp = getSharedPreferences(ConstantFile.NewsPage, MODE_PRIVATE);
        MultiDex.install(this);
        LeakCanary.install(this);
      //  8b85b89c9f64861d4d5738e0813ffd2d
        PlatformConfig.setWeixin("wxa57b6aa6eb469d41", "9b34ecae4c7d61602b98395d48a5cbed");
        //豆瓣RENREN平台目前只能在服务器端配置
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");
//        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
//        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
//        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
//        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
//        PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");


        super.onCreate();
        if (isDebug(this)) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this);
        ARouter.getInstance().inject(this);
//getMain...
        Handler handlernew = new Handler(Looper.getMainLooper());
        mInstance = this;
//        chrome://inspect/#devices
//        Stetho.initializeWithDefaults(this);
        Fresco.initialize(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
//        Fresco.initialize(this,getImagePipelineConfig(this));
//        ImagePipelineConfig config = ImagePipelineConfig.newBuilder()
//                .setBitmapMemoryCacheParamsSupplier(bitmapCacheParamsSupplier)
//                .setCacheKeyFactory(cacheKeyFactory)
//                .setEncodedMemoryCacheParamsSupplier(encodedCacheParamsSupplier)
//                .setExecutorSupplier(executorSupplier)
//                .setImageCacheStatsTracker(imageCacheStatsTracker)
//                .setMainDiskCacheConfig(mainDiskCacheConfig)
//                .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
//                .setNetworkFetchProducer(networkFetchProducer)
//                .setPoolFactory(poolFactory)
//                .setProgressiveJpegConfig(progressiveJpegConfig)
//                .setRequestListeners(requestListeners)
//                .setSmallImageDiskCacheConfig(smallImageDiskCacheConfig)
//                .build();

    }




    public static MyAppAplication getInstance() {
        return mInstance;
    }



    public boolean isDebug(MyAppAplication context){

        boolean isDebug = context.getApplicationInfo()!=null&&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        return isDebug;
    }


      public void add(Disposable disposable){
          compositeDisposable.add(disposable);

      }
      public void cleanAllDisposable(){
        compositeDisposable.clear();
      }



}


