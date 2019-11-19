package com.example.momo.activitys;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = "/activitys/SearchActivity")
public class SearchActivity extends BaseActivity {


    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_guanggao)
    SimpleDraweeView searchGuanggao;
    @BindView(R.id.id_flowlayout02)
    TagFlowLayout idFlowlayout02;
    @BindView(R.id.sercher_expand)
    Button sercherExpand;
    @BindView(R.id.id_flowlayout01)
    TagFlowLayout idFlowlayout01;

    @Override
    public void initViews(Bundle savedInstanceState)   {
        sercherExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idFlowlayout01.getVisibility()==View.GONE){
                    sercherExpand.setText("收起");
                    idFlowlayout01.setVisibility(View.VISIBLE);
                }else {
                    sercherExpand.setText("展开");
                    idFlowlayout01.setVisibility(View.GONE);

                }

            }
        });
        searchGuanggao.setImageURI(Uri.parse("https://ojlty2hua.qnssl.com/image-1570614460519-MDQucG5n.png"));

        List<ArrayList<String>> tag = getSearchAll();
        ArrayList<String> searching = getSearching();


        idFlowlayout.setAdapter(new TagAdapter<String>(tag.get(0)) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv,
                        idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
        idFlowlayout01.setAdapter(new TagAdapter<String>(tag.get(1)) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv,
                        idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });

        idFlowlayout02.setAdapter(new TagAdapter<String>(searching) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv,
                        parent, false);
                tv.setText(s);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(parent.getContext(),""+s,1000);
                    }
                });
                return tv;
            }
        });
    }

    private ArrayList<String> getSearching() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("海贼王");
        arrayList.add("C语言");
        arrayList.add("android开发");


        return arrayList;
    }

    private List<ArrayList<String>> getSearchAll() {
         ArrayList<ArrayList<String>> arrayLists=   new ArrayList<ArrayList<String>>();
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> arrayList01= new ArrayList<String>();
        arrayList.add("明日方舟");
        arrayList.add("鬼灭之刃");
        arrayList.add("RunningMan");
        arrayList.add("熬厂长");
        arrayList.add("满满喜欢你");
        arrayList.add("影流之主");
        arrayList01.add("王者荣耀");
        arrayList01.add("英雄联盟");
        arrayList01.add("android");
        arrayList01.add("Java");
        arrayList01.add("Kotlin");
        arrayList01.add("新番");
        arrayList01.add("十月新番");


        arrayLists.add(arrayList);
        arrayLists.add(arrayList01);
        return arrayLists;


    }

    @Override
    public void initToolBar() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.search_activity_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
