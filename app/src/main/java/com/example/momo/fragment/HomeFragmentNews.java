package com.example.momo.fragment;

import android.content.SharedPreferences;
import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.rxbus.RxBus;
import com.example.momo.R;
import com.example.momo.adpater.UsHeadAdapter01;
import com.example.momo.adpater.ViewHolder;
import com.example.momo.adpater.Viewpager_Adapter;
import com.example.momo.base.LazyFragment;
import com.example.momo.base.MyAppAplication;
import com.example.momo.constants.MyApi;
import com.example.momo.dao.entity.ChaofanBanJiang;
import com.example.momo.dao.entity.News;
import com.example.momo.network.RetrofitHelper;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.SpacesItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeFragmentNews extends LazyFragment {
    @BindView(R.id.news_recycleview)
    RecyclerView newsRecycleview;
    List<News.NewslistBean> newslistBeans = new ArrayList<News.NewslistBean>();
    private int type;
    private int page = 1;
    private SparseArray<Integer> sparseArray;
private int mPosition=2;
boolean isFirst=true;
int size;

    @Override
    protected void loadData() {


        RetrofitHelper.getRecommendAPI(MyApi.getBashUri()).listRepos(page).compose(this.bindToLifecycle())
                .filter(news -> {
                    if (news.getCode() == 200) {

                        return true;
                    }
                    return false;
                }).compose(this.bindToLifecycle()).
                map(new Func1<News, List<News.NewslistBean>>() {
                    @Override
                    public List<News.NewslistBean> call(News news) {
                        return news.getNewslist();
                    }
                }).compose(this.bindToLifecycle()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<News.NewslistBean>>() {
                    @Override
                    public void onCompleted() {
                        ((UsHeadAdapter01) newsRecycleview.getAdapter()).setDatas(newslistBeans);

                        ((UsHeadAdapter01) newsRecycleview.getAdapter()).notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<News.NewslistBean> list) {
                        size= list.size();
//                        if(isFirst){
//                            isFirst=false;
//                            News.NewslistBean newslistBean= new News.NewslistBean();
//                            newslistBean.setChangeLayout(true);
//                            list.add(2,newslistBean);
//                        }
////
                        newslistBeans.addAll( 0,list);
//                        Collections.reverse(newslistBeans);

                    }
                });


    }

    @Override
    protected boolean enableRefush() {
        return true;
    }


    private void finishTask() {
        ChaofanBanJiang chaofanBanJiang = new ChaofanBanJiang();

        ArrayList<String> data = new ArrayList<String>();
         sparseArray = new SparseArray<Integer>();
        sparseArray.put(2, 0);
        type = sparseArray.keyAt(0);

//        new RcHeadAdapter()
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//         dividerItemDecoration.setDrawable( ContextCompat.getDrawable(getContext(),R.drawable.reycylerview_space_line));
        newsRecycleview.setLayoutManager(linearLayoutManager);
        newsRecycleview.addItemDecoration(new SpacesItemDecoration(10, false));
        newsRecycleview.addItemDecoration(dividerItemDecoration);

        newsRecycleview.setAdapter(new UsHeadAdapter01<News.NewslistBean>(getContext(), newslistBeans) {
            @Override
            protected int getLayoutId(int viewType) {
                LogUtil.e(viewType+"position"+newslistBeans.get(viewType).getChangeLayout());
                int layoutid = R.layout.item_new;
                if (viewType==2) {

//
                    layoutid = R.layout.item_viewpager;
                }

                return layoutid;
            }

            @Override
            protected void convert(ViewHolder holder, int position, News.NewslistBean mData) {
LogUtil.e(position+"'mData.getChangeLayout()"+mData.getChangeLayout());
               //可以封装在bean类 传进去布局自动判断
                if (position==2) {

                    holder.setViewPagerAdapter(300, R.id.indicator_circle, R.id.vp, getBander().size(), getBander().get(0), new Viewpager_Adapter(getContext(), getBander()));


                } else {

                    holder.setOnClickListener(R.id.card_layout, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChaofanBanJiang chaofanBanJiang = new ChaofanBanJiang();
                            chaofanBanJiang.setName("xiaoya");
                            EventBus.getDefault().postSticky(chaofanBanJiang);
                            ARouter.getInstance().build("/activityss/WeViewActivity1")
                                    .withString("url", mData.getUrl()).navigation();

                            LogUtil.e("新闻 card" + mData.getUrl());
                        }
                    });
                    holder.setText(R.id.news_detail, mData.getDescription()).setText(R.id.news_time, mData.getCtime()).
                            setText(R.id.new_title, mData.getTitle()).setImageUrl(R.id.news_image, mData.getPicUrl());
                }


            }


        });


    }

    @Override
    public int getLayoutById() {
        return R.layout.home_news_layout;
    }


    @Override
    public void initData(boolean f) {
        LogUtil.e("news+init");

        finishTask();
        RxBus.getDefault().subscribeSticky(this, new RxBus.Callback<ChaofanBanJiang>() {


            @Override
            public void onEvent(ChaofanBanJiang chaofanBanJiang) {
                LogUtil.e("finishTask-----" + chaofanBanJiang.getName());
            }
        });

    }

    @Override
    public void onPause() {


        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = MyAppAplication.getInstance().sp.edit();
        editor.putInt("page", page);
        editor.commit();
    }

    @Override
    public void onResume() {

        page = MyAppAplication.getInstance().sp.getInt("page", 0);


        super.onResume();
    }

    @Override
    public boolean useCache() {
        return false;
    }

    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

        swipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {


        page = page + 1;
        loadData();
        super.onRefresh();
    }


    private ArrayList<String> getBander() {
        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570964079259&di=ed75b6b6ec8beb397916671d19e1dc85&imgtype=0&src=http%3A%2F%2Fwww.gov.cn%2Fjrzg%2Fimages%2Fimages%2F0011431a94e60b0e43f101.jpg");
        arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570963611020&di=e2acda68cc72625d34275d1b700877a5&imgtype=0&src=http%3A%2F%2Fp1.img.cctvpic.com%2Fphotoworkspace%2Fcontentimg%2F2015%2F09%2F03%2F2015090317302311596.jpg");
        arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570963454068&di=12bb3dc86092e9609acdbdb86295d840&imgtype=0&src=http%3A%2F%2Fp0.so.qhimgs1.com%2Ft01ea04f0c923a0f454.jpg");

        return arrayList;

    }


}
