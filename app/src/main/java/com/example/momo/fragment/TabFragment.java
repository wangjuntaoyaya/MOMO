package com.example.momo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momo.R;
import com.example.momo.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment
{
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView mRecyclerView;
    // private TextView mTextView;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

//        getLayoutInflater().inflate(R.layout.fragment_tab,container,false);
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        for (int i = 0; i < 50; i++) {
            mDatas.add("news" + " -> " + i);
        }
        mRecyclerView = (RecyclerView) view
                .findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mRecyclerView.setAdapter(new RcHeadAdapter(getContext(),mDatas ));

        return view;

    }

    public static TabFragment newInstance(String title)
    {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onResume() {
        LogUtil.e("onResumeonResumeonResume");
        super.onResume();
    }
}
