package com.example.momo.adpater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.momo.base.BaseFragment;
import com.example.momo.utils.LogUtil;

import java.util.List;


public class NewsFragmentAdpater extends FragmentPagerAdapter {

    private List<BaseFragment> list;
    public NewsFragmentAdpater(@NonNull FragmentManager fm, int behavior,List<BaseFragment> l) {
        super(fm, behavior);
        this.list = l;
    }
    @Override
    public Fragment getItem(int position) {
       return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        LogUtil.e("getPageTitle");
        return "行文";
    }
}
