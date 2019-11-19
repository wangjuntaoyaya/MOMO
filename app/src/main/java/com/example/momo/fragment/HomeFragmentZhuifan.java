package com.example.momo.fragment;

import android.util.SparseArray;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.rxbus.RxBus;
import com.example.momo.R;
import com.example.momo.adpater.UsHeadAdapter;
import com.example.momo.adpater.UsHeadAdapter02;
import com.example.momo.adpater.ViewHolder;
import com.example.momo.adpater.Viewpager_Adapter;
import com.example.momo.base.BaseActivity;
import com.example.momo.base.LazyFragment;
import com.example.momo.dao.entity.ChaofanBanJiang;
import com.example.momo.dao.entity.ZhuiFanInfo;
import com.example.momo.network.RetrofitHelper;
import com.example.momo.network.api.ApiConstants;
import com.example.momo.utils.CommonUtil;
import com.example.momo.utils.FileUtil;
import com.example.momo.utils.ImageUtil;
import com.example.momo.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeFragmentZhuifan extends LazyFragment {

    @BindView(R.id.zhuifan_parent_recyclerview)
    RecyclerView zhuifanParentRecyclerview;
    ArrayList<ZhuiFanInfo.ZuipanBean> zuipanBean;
    ArrayList<ZhuiFanInfo.EpreBean> epreBeans;
    ArrayList<ZhuiFanInfo.OverBean> overBeans;
  private void   getRaw(){

      zuipanBean = new ArrayList<ZhuiFanInfo.ZuipanBean>();
      epreBeans = new ArrayList<ZhuiFanInfo.EpreBean>();
      overBeans = new ArrayList<ZhuiFanInfo.OverBean>();
      //raw文件夹
      FileUtil fileUtil=new FileUtil<ZhuiFanInfo>();
      ZhuiFanInfo zhuiFanInfo= new ZhuiFanInfo();
      zhuiFanInfo  = (ZhuiFanInfo) new FileUtil().jsonToT(  FileUtil.fromRaw(getContext(),R.raw.zuifan),zhuiFanInfo.getClass());
     int count= zhuiFanInfo.getZuipan().size();
     for(int i=0;i<count;i++){
         zuipanBean.add( zhuiFanInfo.getZuipan().get(i)) ;

     }
      for(int i=0;i<zhuiFanInfo.getEpre().size();i++){
          epreBeans.add( zhuiFanInfo.getEpre().get(i)) ;

      }
      for(int i=0;i<zhuiFanInfo.getOver().size();i++){
          overBeans.add( zhuiFanInfo.getOver().get(i)) ;

      }
      finshTask();


    }
    boolean isResumed=false;
  //或者图片比例 是否完成
    boolean isAccept=false;
    @Override
    protected void loadData() {
        isResumed=true;
      //如果resume 则调用加载数据 但是取宽高的 任务还没结束就不加载数据 等获取完再回调
      if(!isAccept){
          return;
      }

        getRaw();

        if(true)return;
        //网络获取
        zuipanBean = new ArrayList<ZhuiFanInfo.ZuipanBean>();
        epreBeans = new ArrayList<ZhuiFanInfo.EpreBean>();
        overBeans = new ArrayList<ZhuiFanInfo.OverBean>();
        List<ArrayList<?>> lists = new ArrayList<ArrayList<?>>();

        RetrofitHelper.getRecommendAPI(ApiConstants.BASE_URL).getFanjuInfo().compose(this.bindToLifecycle())
                .flatMap(new Func1<ZhuiFanInfo, Observable<?>>() {
                    @Override
                    public Observable<?> call(ZhuiFanInfo zhuiFanInfo) {


//                        return Observable.from(zhuiFanInfo.getOver());
                        return Observable.concat(Observable.from(zhuiFanInfo.getZuipan()), Observable.from(zhuiFanInfo.getEpre()), Observable.from(zhuiFanInfo.getOver()));


                    }
                }).compose(this.bindToLifecycle()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e("onCompleted" + overBeans.size());
                        lists.add(zuipanBean);
                        lists.add(epreBeans);
                        lists.add(overBeans);
                        finshTask();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if (o instanceof ZhuiFanInfo.ZuipanBean) {

                            zuipanBean.add((ZhuiFanInfo.ZuipanBean) o);

                        }
                        if (o instanceof ZhuiFanInfo.EpreBean) {

                            epreBeans.add((ZhuiFanInfo.EpreBean) o);

                        }
                        if (o instanceof ZhuiFanInfo.OverBean) {

                            overBeans.add((ZhuiFanInfo.OverBean) o);

                        }

                    }
                });


    }

    @Override
    protected boolean enableRefush() {
        return false;
    }

    @Override
    public int getLayoutById() {
        return R.layout.home_zhuifan_layout;
    }

    @Override
    public void initData(boolean f) {
      LogUtil.e("zhuipan+init");
//        RxBus.getDefault().subscribeSticky(this, new RxBus.Callback<ChaofanBanJiang>() {
//            @Override
//            public void onEvent(ChaofanBanJiang chaofanBanJiang) {
//                LogUtil.e("ChaofanBanJiang"+chaofanBanJiang.getName());
//            }
//        });


        ChaofanBanJiang chaofanBanJiang=  new ChaofanBanJiang();
        chaofanBanJiang.setName("juntao");

        RxBus.getDefault().postSticky(chaofanBanJiang,"tag");



    }
    private void finshTask() {


        SparseArray sparseArray = new SparseArray<Integer>();

        sparseArray.put(0, R.layout.fragment_zhuipan_01);
        sparseArray.put(1, R.layout.fragment_zhuipan_02);
        sparseArray.put(2, R.layout.fragment_zhuipan_02);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.recycle_decoration_divider));

        zhuifanParentRecyclerview.addItemDecoration(dividerItemDecoration);
        zhuifanParentRecyclerview.setLayoutManager(linearLayoutManager);
        zhuifanParentRecyclerview.setAdapter(new UsHeadAdapter02(getContext(), sparseArray.size(), sparseArray) {


            @Override
            protected void convert(ViewHolder holder, int position) {

                int key = sparseArray.keyAt(position);
                switch (key) {

                    case 0: {
                        holder.setViewPagerAdapter(height,R.id.indicator_circle,R.id.vp, getArrayList().size(),getArrayList().get(0),new
                                Viewpager_Adapter(getContext(),  getArrayList()));
                        holder.setRecyclerViewAdapter(R.id.zhuipan_horizontal_recyclerview, true,false, 0,
                                new UsHeadAdapter<ZhuiFanInfo.ZuipanBean>(getContext(), zuipanBean) {
                            @Override
                            protected int getLayoutId(int viewType) {
                                return R.layout.fragment_zhuipan_layout02_item;
                            }

                            @Override
                            protected void convert(ViewHolder holder, int position, ZhuiFanInfo.ZuipanBean mData) {
                                holder.setImageUrlGlid(R.id.zhuipan_image, mData.getImage(),3);
                                holder.setText(R.id.zhuipan_renewtitle, mData.getRenewtitle());
                                holder.setText(R.id.zhuipan_title, mData.getTitle());
                                holder.setText(R.id.zhuipan_watch_title, mData.getWatchtitle());

                            }


                        });

                        break;
                    }
                    case 1: {
                        holder.setText(R.id.zhuipan_expend,"番剧推荐");
                        holder.setRecyclerViewAdapter(R.id.zhuipan_grid_recyclerview, false, true, 2, new UsHeadAdapter<ZhuiFanInfo.EpreBean>(getContext(),epreBeans) {
                            @Override
                            protected int getLayoutId(int viewType) {
                                return R.layout.fragment_zhuipan_layout02_item;
                            }

                            @Override
                            protected void convert(ViewHolder holder, int position, ZhuiFanInfo.EpreBean mData) {

                                holder.setImageUrlGlid(R.id.zhuipan_image, mData.getImage(),2);
                                holder.setText(R.id.zhuipan_renewtitle, mData.getRenewtitle());
                                holder.setText(R.id.zhuipan_title, mData.getTitle());
                                holder.setText(R.id.zhuipan_watch_title, mData.getWatchtitle());
                            }
                        });







                        break;
                    }
                    case 2:{
                        holder.setText(R.id.zhuipan_expend,"精选番剧");
                        holder.setRecyclerViewAdapter(R.id.zhuipan_grid_recyclerview, false, true, 2, new UsHeadAdapter<ZhuiFanInfo.OverBean>(getContext(),overBeans) {
                            @Override
                            protected int getLayoutId(int viewType) {
                                return R.layout.fragment_zhuipan_layout02_item;
                            }

                            @Override
                            protected void convert(ViewHolder holder, int position, ZhuiFanInfo.OverBean mData) {
                                holder.setImageUrlGlid(R.id.zhuipan_image, mData.getImage(),2);
                                holder.setText(R.id.zhuipan_renewtitle, mData.getRenewtitle());
                                holder.setText(R.id.zhuipan_title, mData.getTitle());
                                holder.setText(R.id.zhuipan_watch_title, mData.getWatchtitle());
                            }
                        });





                    }


                }


            }
        });


    }

    private ArrayList<String> getArrayList() {

        ArrayList arrayList=new ArrayList<String>();

        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/59c9872178c7e462be3d573d414021fb5c930f24.png@2320w_664h.png");
        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/b465c283ee70c69d5e864fc7a9ae5c0482a9edbd.jpg@2320w_664h.jpg");
        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/f6ea2ae21621d6581c023a615346921a13425a26.jpg@2320w_664h.jpg");
        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/4bafb50fdb5a6d61021b7f360ab0407ab7976a94.jpg@2320w_664h.jpg");
        arrayList.add("https://i0.hdslb.com/bfs/bangumi/image/f5277910293a61afee7c3deb122ebbdc2cb3b054.jpg@2320w_664h.jpg");
        return  arrayList;

    }

    @Override
    public boolean useCache() {
        return false;
    }

    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

    }
private  int height=0;
    @Override
    public void onStart() {
        isAccept=false;
        ImageUtil.adapterHeight(getContext(), getArrayList().get(0), CommonUtil.getScreenPix((BaseActivity) getContext())[0], new ImageUtil.OnLoaddImageResultListener() {
            @Override
            public void getHeight(int h) {

                isAccept=true;
                height=h;
                //如果调用了loadData 但是 这个任务还没结束 再次触发 isResumed默认为false
                if(isResumed){

                    loadData();
                }

            }
        });

        super.onStart();
    }
}
