package com.example.momo.activitys;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.network.RetrofitHelper;
import com.example.momo.utils.LogUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class EditActivity extends BaseActivity {


    @BindView(R.id.icon_layout)
    ViewPager iconLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void initViews(Bundle savedInstanceState)   {

//        new MyTask().execute("","");
//       Future<String> future= Executors.newFixedThreadPool(3).submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "";
//            }
//        });

        iconLayout.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {


                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                ImageView imageView= new ImageView(container.getContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setImageResource(R.drawable.ic_03);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                return imageView;
            }

                                  @Override
                                  public int getItemPosition(@NonNull Object object) {
                                      return super.getItemPosition(object);
                                  }
                              }
        );

        iconLayout.setPageMargin(20);
        iconLayout.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

            float scan=0.6f;

//            }
                     if(position>1|position<-1) return;
                    if (position > 0) {
                        LogUtil.e("position 1  " + position);
                        page.setScaleX(1 - scan * position);
                        page.setScaleY(1 - scan * position);}
          if (position < 0) {

                            LogUtil.e("position2" + 1 + scan * position);
                            page.setScaleX(1 + scan * position);
                            page.setScaleY(1 + scan * position);

                        }





                }}
        );
        final String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE};
        Cursor c = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null);

        if (c.moveToFirst()) {

            LogUtil.e(" c.getString(c.getColumnIndex(MediaStore.Files.FileColumns.DATA));" + c.getColumnIndex(MediaStore.Files.FileColumns.SIZE));
            LogUtil.e(" c.getString(c.getColumnIndex(MediaStore.Files.FileColumns.DATA));" + c.getCount());
        }
        c.moveToNext();
        LogUtil.e(" c.getString(c.getColumnIndex(MediaStore.Files.FileColumns.DATA));" + c.getColumnIndex(MediaStore.Files.FileColumns.SIZE));
        LogUtil.e(" c.getString(c.getColumnIndex(MediaStore.Files.FileColumns.DATA));" + c.getString(c.getColumnIndex(MediaStore.Files.FileColumns.DATA)));
        Cursor cc = getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection, null, null);

        if (cc.moveToFirst()) {

            LogUtil.e(" c.getString(c.getColumnIndex(MediaStore.Files.FileColumns.DATA));" + cc.getColumnIndex(MediaStore.Files.FileColumns.SIZE));
            LogUtil.e(" c.getString(c.getColumnIndex(MediaStore.Files.FileColumns.DATA));" + cc.getString(cc.getColumnIndex(MediaStore.Files.FileColumns.DATA)));
        }
        download();
    }


    @Override
    protected void onStart() {


        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void download() {


        RetrofitHelper.downLoad("http://192.168.1.100:8080/").downloadImage().compose(this.bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ResponseBody>() {
            @Override
            public void call(ResponseBody responseBody) {
                try {
                    LogUtil.e("responseBody" + responseBody.contentType() + "responseBody" + responseBody.bytes().length);
                } catch (IOException e) {
                    LogUtil.e("e" + e.toString());
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public void initToolBar() {

    }
   class MyTask extends AsyncTask<String, Integer, String> {


       @Override
       protected String doInBackground(String... strings) {
           return null;
       }

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected void onPostExecute(String s) {
           super.onPostExecute(s);
       }

       @Override
       protected void onProgressUpdate(Integer... values) {
           super.onProgressUpdate(values);
       }

       @Override
       protected void onCancelled(String s) {
           super.onCancelled(s);
       }

       @Override
       protected void onCancelled() {
           super.onCancelled();
       }
   }
    @Override
    protected int getLayoutID() {
        return R.layout.item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
