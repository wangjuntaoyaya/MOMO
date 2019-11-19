package com.example.momo.fragment;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.adpater.UsHeadAdapter;
import com.example.momo.adpater.ViewHolder;
import com.example.momo.adpater.listener.OnItemCheckListener;
import com.example.momo.base.LazyFragment;
import com.example.momo.dao.entity.RelativeVideoInfo;
import com.example.momo.network.RetrofitHelper;
import com.example.momo.network.api.ApiConstants;
import com.example.momo.network.qiniu.QiNiuHelper;
import com.example.momo.utils.ToastUtils;
import com.example.momo.widget.SpacesItemDecoration;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RlativePlayerFragment extends LazyFragment {


    @BindView(R.id.relate_recyclerview)
    RecyclerView relateRecyclerview;

    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {


    }

    @Override
    protected void loadData() {

        RetrofitHelper.getRecommendAPI(ApiConstants
                .BASE_URL).getRelativeVideoIndo().compose(this.bindToLifecycle()).filter(new Func1<RelativeVideoInfo, Boolean>() {
            @Override
            public Boolean call(RelativeVideoInfo relativeVideoInfo) {
                if (relativeVideoInfo.getCode() == 200) {
                    return true;
                }

                return false;
            }
        }).map(new Func1<RelativeVideoInfo, List<RelativeVideoInfo.ResultBean.RelationBean>>() {
            @Override
            public List<RelativeVideoInfo.ResultBean.RelationBean> call(RelativeVideoInfo relativeVideoInfo) {
                return relativeVideoInfo.getResult().getRelation();
            }
        }).compose(this.bindToLifecycle()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<RelativeVideoInfo.ResultBean.RelationBean>>() {
                    @Override
                    public void call(List<RelativeVideoInfo.ResultBean.RelationBean> relationBeans) {
                        finishTask(relationBeans);
                    }
                });


    }

    @Override
    protected boolean enableRefush() {
        return false;
    }

    private void finishTask(List<RelativeVideoInfo.ResultBean.RelationBean> b) {
      ArrayList<String> tags=getTags();
        ArrayList<Integer> viewIds;
        ArrayList<String> datas;
        viewIds=initRadioViewIds();
        datas=initRadioViewDatas();

        SparseArray<Integer> sparseArraynew =new SparseArray<Integer>();
        sparseArraynew.put(0,0);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration=   new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable( ContextCompat.getDrawable(getContext(),R.drawable.reycylerview_space_line));

        relateRecyclerview.addItemDecoration(new SpacesItemDecoration(10,false));
        relateRecyclerview.addItemDecoration(dividerItemDecoration);
        relateRecyclerview.setLayoutManager(linearLayoutManager);

        relateRecyclerview.setAdapter(new UsHeadAdapter<RelativeVideoInfo.ResultBean.RelationBean>(getContext(),b) {
            @Override
            protected int getLayoutId(int viewType) {
                int layoutid=R.layout.item_relation;
                if(viewType==0){
                    layoutid=R.layout.expand_text_layout;

                }


                return layoutid;
            }

            @Override
            protected void convert(ViewHolder holder, int position, RelativeVideoInfo.ResultBean.RelationBean mData) {

                if(position==0){
                    holder.setCheckBoxLayoutText(R.id.video_linearLayout, viewIds, datas, new OnItemCheckListener() {
                        @Override
                        public void cheakState(boolean b, int positon) {
                         switch (position){


                             case 0:{
                                 if(b){
                                     ToastUtils.showToast(getContext(),""+"like",500);
                                 }else {
                                     ToastUtils.showToast(getContext(),""+"not",500);
                                 }
                                 break;
                             }

                             case 1:{

                                 break;
                             }
                             case 2:{

                                 break;
                             }
                             case 3:{

                                 break;
                             }
                             case 4:{

                                 break;
                             }

                         }

                        }
                    });


                     holder.setFlowlayout(R.id.id_flowlayout, new TagAdapter<String>(tags) {
                         @Override
                         public View getView(FlowLayout parent, int position, String s) {
                             View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv, parent, false);
                             TextView textView = view.findViewById(R.id.item_text);
                             textView.setClickable(true);


                             textView.setText(s);
                             return textView;
                         }
                     }, new TagFlowLayout.OnTagClickListener() {
                         @Override
                         public boolean onTagClick(View view, int position, FlowLayout parent) {

                             return false;
                         }
                     });
                    holder.setImageUrl(R.id.video_user_pic, new QiNiuHelper().downLoad("02.jpg"));




                }else {



                    holder.setImageUrl(R.id.relation_image,mData.getImage_url()).setText(R.id.relation_title,mData.getTitle())
                            .setButtonText(R.id.relation_up_name,mData.getUp_name()).setRadioGraupButtonText(R.id.relation_watch,mData.getWatch())
                            .setRadioGraupButtonText(R.id.relation_danmu,mData.getDanmu());
                }



            }


        });

    }

    private ArrayList<String> initRadioViewDatas() {
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("1587");
        arrayList.add("不喜欢");
        arrayList.add("800");
        arrayList.add("654");
        arrayList.add("230");
        return  arrayList;

    }

    private ArrayList<Integer> initRadioViewIds() {

        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        arrayList.add(R.id.like);
        arrayList.add(R.id.not_like);
        arrayList.add(R.id.coin);
        arrayList.add(R.id.collect);
        arrayList.add(R.id.share);
        return  arrayList;
    }

    private ArrayList<String> getTags() {
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("美食");
        arrayList.add("日常");
        arrayList.add("美好的星期天"); arrayList.add("vlog");



        return  arrayList;

    }

    @Override
    public int getLayoutById() {
        return R.layout.fragment_relative_layout;
    }

    @Override
    public void initData(boolean f) {

    }

    @Override
    public boolean useCache() {
        return false;
    }
}
