package com.example.momo.fragment;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.adpater.ImageAdapter;
import com.example.momo.base.LazyFragment;
import com.example.momo.base.MyAppAplication;
import com.example.momo.dao.entity.ImageEntity;
import com.example.momo.dao.entity.ImageInfo;
import com.example.momo.network.RetrofitHelper;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.NotScrollLinearLayoutManager;
import com.example.momo.widget.SpacesItemDecoration;
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ShopFragmentBaobuLiu extends LazyFragment {
    Handler handler=  new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message msg) {
            swipeRefreshLayout.setRefreshing(false);
            super.handleMessage(msg);

        }
    };

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private int page = 1;
    private ImageAdapter imageAdapter;
    private List<ImageEntity.ImagesBean> list = new ArrayList<ImageEntity.ImagesBean>();
    private NotScrollLinearLayoutManager staggeredGridLayoutManager;


    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
//    protected void loadData() {
//////  LogUtil.e("loadDatabaobuliu");
//////
//////
//////        RetrofitHelper.getImageService().getImageUrls(page).compose(bindToLifecycle()).filter(new Func1<ImageEntity, Boolean>() {
//////            @Override
//////            public Boolean call(ImageEntity imageEntity) {
//////                return imageEntity.getResult() == 200;
//////            }
//////        })
//////                .map(new Func1<ImageEntity, List<ImageEntity.ImagesBean>>() {
//////
//////
//////                    @Override
//////                    public List<ImageEntity.ImagesBean> call(ImageEntity imageEntity) {
//////
//////                        return imageEntity.getImages();
//////                    }
//////                }).compose(this.bindToLifecycle()).subscribeOn(Schedulers.io()).doOnCompleted(new Action0() {
//////            @Override
//////            public void call() {
//////                swipeRefreshLayout.setRefreshing(false);
//////
//////            }
//////        })
//////
//////                .observeOn(AndroidSchedulers.mainThread())
//////                .subscribe(imageAdapter, throwable -> erro(throwable));
//////
//////
//////    }


    protected void loadData() {



        RetrofitHelper.getImageService().getImageUrls(page).compose(bindToLifecycle()).filter(new Func1<ImageEntity, Boolean>() {
            @Override
            public Boolean call(ImageEntity imageEntity) {
                return imageEntity.getResult() == 200;
            }
        })
                .map(new Funny()).compose(bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).cache()
                .subscribe(imageAdapter,throwable -> erro(throwable));


    }

    private void erro(Throwable throwable) {
        LogUtil.e("throwable"+throwable.getMessage().toString());
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    protected boolean enableRefush() {
        return false;
    }

    @Override
    public int getLayoutById() {
        return R.layout.fragment_shop_baobuliu_layout;
    }

    @Override
    public void initData(boolean f) {

        Disposable d= RxRecyclerView.scrollEvents(recyclerView).subscribe(new Consumer<RecyclerViewScrollEvent>() {
            @Override
            public void accept(RecyclerViewScrollEvent recyclerViewScrollEvent) throws Exception {

              int [] ints=  staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(new int[2]);

LogUtil.e("RxRecyclerViewRxRecyclerView'"+page);
              if((ints[0])+2>= imageAdapter.getItemCount()){
                  imageAdapter.setRefreshState(false);

//                  if(page==5)return;
                  page=1+page;

                  loadData();

              }

            }
        });
        MyAppAplication.getInstance().add(d);

         staggeredGridLayoutManager = new NotScrollLinearLayoutManager(2, RecyclerView.VERTICAL);


        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        imageAdapter = new ImageAdapter(getContext(), imageInfos, new SparseArray<Integer>());
        recyclerView.addItemDecoration(new SpacesItemDecoration(getContext(),true));
        recyclerView.setAdapter(imageAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });


    }

private List<ImageInfo> imageInfos=new ArrayList<ImageInfo>();

  class Funny implements Func1<ImageEntity, List<ImageInfo>> {


      @Override
      public List<ImageInfo> call(ImageEntity ImagesBean) {

          for(ImageEntity.ImagesBean bean :ImagesBean.getImages()){
              try {
                  LogUtil.e("ImagesBean.getImages()"+ImagesBean.getImages().size());

                  Bitmap bitmap= Picasso.get().load(bean.getUrl()).get();

                  ImageInfo imageInfo=  new ImageInfo();
                  imageInfo.setWidth(bitmap.getWidth());
                  imageInfo.setHegiht(bitmap.getHeight());
                  imageInfo.setUrl(bean.getUrl());
                  imageInfos.add(imageInfo);
                  LogUtil.e("imageInfos"+imageInfos.size());

              } catch (IOException e) {

                  e.printStackTrace();
              }

          }



          return imageInfos;
      }
  }

    @Override
    public boolean useCache() {
        return false;
    }

    private ArrayList<String> initUrls() {
        String[] strings = getContext().getResources().getStringArray(R.array.images);

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(strings[i]);

        }
        return arrayList;


    }

    @Override
    public void onRefresh() {
        page=1;
//       if(page==0){
//           swipeRefreshLayout.setRefreshing(false);
//       }
        imageAdapter.setRefreshState(true);

        LogUtil.e("'onRefresh"+page);
        loadData();
        timer();
        super.onRefresh();
    }

    private void timer(){

      new Timer().schedule(new TimerTask() {
          @Override
          public void run() {
              handler.obtainMessage(0).sendToTarget();

          }
      },1000);

    }




}
