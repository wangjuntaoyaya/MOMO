package com.example.momo.fragment;

import android.os.Build;
import android.view.View;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.adpater.RcHeadAdapter;
import com.example.momo.base.LazyFragment;
import com.example.momo.dao.entity.RecomendsInfo;
import com.example.momo.network.RetrofitHelper;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeFragmentRe extends LazyFragment {
    @BindView(R.id.re_recycleview)
    RecyclerView reRecycleview;
    @BindView(R.id.re_refresh)
    LinearLayoutCompat reRefresh;

    @Override
    protected void loadData() {



//         .doOnSubscribe(this::showProgressBar)



    }

    @Override
    protected boolean enableRefush() {

        return false;
    }


    @Override
    public int getLayoutById() {


        return R.layout.home_re_layout;
    }

    @Override
    public boolean useCache() {
        return false;
    }

    @Override
    public void initData(boolean b) {
        ArrayList< RecomendsInfo.ResultBean.UsersBean> usersBeans =new ArrayList<RecomendsInfo.ResultBean.UsersBean>();
        ArrayList<String> banders =new ArrayList<String>();



        RetrofitHelper.getRecommendAPI(RetrofitHelper.VIDEO_BASEURL).getVedioInfo().compose(this.bindToLifecycle()).
                flatMap(new Func1<RecomendsInfo, Observable<?>>() {
                    @Override
                    public Observable<RecomendsInfo.ResultBean.UsersBean> call(RecomendsInfo recomendsInfo) {
                        LogUtil.e("bannerBeans-----------"+recomendsInfo.getResult().getBanner().size());

                        List<RecomendsInfo.ResultBean.BannerBean> bannerBeans= recomendsInfo.getResult().getBanner();

                        for(int i=0;i<bannerBeans.size();i++){

                            banders.add( bannerBeans.get(i).getUrl());


                        }

                        return Observable.from(recomendsInfo.getResult().getUsers());
                    }
                }).compose(this.bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {


                               @Override
                               public void onCompleted() {
                                   LogUtil.e("onCompleted+++++++++");
                                   initRecyclerView(usersBeans,banders)  ;


                               }

                               @Override
                               public void onError(Throwable e) {

                                   LogUtil.e("Throwable+++++++++"+e.toString());
                               }

                               @Override
                               public void onNext(Object o) {

                                   usersBeans.add((RecomendsInfo.ResultBean.UsersBean)o);
                               }
                           }

                );





    }


    private void initRecyclerView(ArrayList<RecomendsInfo.ResultBean.UsersBean> usersBeans, ArrayList<String> banders) {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        reRecycleview.setLayoutManager(linearLayoutManager);
        reRecycleview.addItemDecoration(new SpacesItemDecoration(20,false));
        DividerItemDecoration dividerItemDecoration=   new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable( ContextCompat.getDrawable(getContext(),R.drawable.recycle_decoration_divider));

        reRecycleview.addItemDecoration(dividerItemDecoration);

        RcHeadAdapter RcHeadAdapter=  new RcHeadAdapter(getContext(),usersBeans,this,banders);

        reRecycleview.setAdapter(RcHeadAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            reRecycleview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {




                }
            });
        }

    }


    @Override
    public void onPause() {

        super.onPause();
    }



    @Override
    public void onRefresh() {

        super.onRefresh();
    }



    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

//        swipeRefreshLayout.setEnabled(true);
//        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {


        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {

        super.onResume();
    }

}
