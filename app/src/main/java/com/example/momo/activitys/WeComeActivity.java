package com.example.momo.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;

import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.AutoButton;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeComeActivity extends BaseActivity {


    @BindView(R.id.splash_image)
    ImageView splashImage;
    @BindView(R.id.auto_btn)
    AutoButton autoBtn;

    private Context mcontext;

    @Override
    public void initViews(Bundle savedInstanceState) {
        mcontext = this;
//        getWindow().setBackgroundDrawableResource(R.drawable.ic_01);
        verifyStoragePermissions(this);

        BufferedInputStream buffin = null;
        BufferedOutputStream buffout = null;

        byte[] buf = new byte[520000];
        File file233 = null;
        try {
            String fileDir = "splash";
            String fileName = "233.png";
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileDir);
            if (!file.exists()) {

                file.mkdirs();
                LogUtil.e("exists" + file.getAbsolutePath().toString());
            }

            file233 = new File(file.getAbsolutePath() + "/" + fileName);
            if (!file233.exists()) {
                LogUtil.e("createNewFile");
                file233.createNewFile();

            }

            buffout = new BufferedOutputStream(new FileOutputStream(file233));

            LogUtil.e("file233" + file233.getAbsolutePath().toString());
            buffin = new BufferedInputStream(getResources().getAssets().open("splash/ic_splash_default.png"));


            int read = -1;

            while (buffin.read(buf) != -1) {
                LogUtil.e("while");
                buffout.write(buf, 0, buf.length);

            }
        } catch (IOException e) {
            LogUtil.e("" + e.getMessage().toString());

        }
        LogUtil.e("file233file233" + file233.length());
        splashImage.setImageURI(Uri.fromFile(file233));
        autoBtn.start();
//        splashImage.setImageResource(R.drawable.ic_01);
//        splashImage.setImageBitmap( BitmapFactory.decodeByteArray(buf,0,buf.length));
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(mcontext, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.spash_no_anima, R.anim.splash_aima);

//               intent2Activity(HomeActivity.class,R.anim.spash_no_anima,R.anim.splash_aima);

            }
        }, 2000);

    }

    @Override
    protected void onStop() {

        super.onStop();
        finish();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    protected int getLayoutID() {
        LogUtil.e("getLayoutID+getLayoutIDgetLayoutID");
        return R.layout.splash_layout;
    }


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static void verifyStoragePermissions(BaseActivity activity) {


        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
