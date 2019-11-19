package com.example.momo.utils;


import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;

import com.example.momo.base.BaseActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

public class CommonUtil {
    static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
    private CommonUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /** Reads an InputStream and converts it to a String. */
    private static String readAsString(InputStream stream) throws IOException {
        StringWriter writer = new StringWriter();
        Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        while (true) {
            int c = reader.read();
            if (c < 0) {
                break;
            }
            writer.write(c);
        }
        return writer.toString();
    }
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * DisplayMetrics类中属性density
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * DisplayMetrics类中属性density
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * DisplayMetrics类中属性scaledDensity
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * DisplayMetrics类中属性scaledDensity
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

//得到屏幕的宽度高度 即像素
    public static int[] getScreenPix(BaseActivity c) {

        DisplayMetrics metrics = new DisplayMetrics();

        c.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        int[] wh = new int[2];

        wh[0] = widthPixels;
        wh[1] = heightPixels;
        return wh;
    }

    /**
     * 检查是否有网络
     */
    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isAvailable();
    }


    /**
     * 检查是否是WIFI
     */
    public static boolean isWifi(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检查是否是移动网络
     */
    public static boolean isMobile(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }


    /**
     * 检查SD卡是否存在
     */
    private static boolean checkSdCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机SD卡总空间
     */
    private static long getSDcardTotalSize() {
        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSizeLong = mStatFs.getBlockSizeLong();
            }
            long blockCountLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockCountLong = mStatFs.getBlockCountLong();
            }
            return blockSizeLong * blockCountLong;
        } else {
            return 0;
        }
    }


    /**
     * 获取手机内部存储总空间
     */
    public static long getPhoneTotalSize() {
        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());

            long blockSizeLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSizeLong = mStatFs.getBlockSizeLong();
            }
            long blockCountLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockCountLong = mStatFs.getBlockCountLong();
            }
            return blockSizeLong * blockCountLong;
        } else {
            return getSDcardTotalSize();
        }
    }


    /**
     * 获取手机内存存储可用空间
     */
    public static long getPhoneAvailableSize() {
        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSizeLong = mStatFs.getBlockSizeLong();
            }
            long availableBlocksLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                availableBlocksLong = mStatFs.getAvailableBlocksLong();
            }
            return blockSizeLong * availableBlocksLong;
        } else
            return getSDcardAvailableSize();
    }

    /**
     * 获取SDka可用空间
     */
    private static long getSDcardAvailableSize() {
        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());

            long blockSizeLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSizeLong = mStatFs.getBlockSizeLong();
            }
            long availableBlocksLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                availableBlocksLong = mStatFs.getAvailableBlocksLong();
            }
            return blockSizeLong * availableBlocksLong;
        } else {
            return 0;
        }
    }
}
