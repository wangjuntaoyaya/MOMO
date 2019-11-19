package com.example.momo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momo.R;
import com.example.momo.adpater.CommonAdapter;
import com.example.momo.adpater.ViewHolder;
import com.example.momo.base.BaseFragment;
import com.example.momo.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment {

    private List<String> mDatas = new ArrayList<String>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           LogUtil.e("onCreateView.........................");
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        for (int i = 0; i < 50; i++) {
            mDatas.add("news" + " -> " + i);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LogUtil.e("initDatainitDatainitData"+mDatas.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.item, mDatas) {


            @Override
            public void convert(ViewHolder holder, String o) {

                LogUtil.e("convert");
//                holder.setText(R.id.id_info, o);
            }
        });

 return view;
    }




}
