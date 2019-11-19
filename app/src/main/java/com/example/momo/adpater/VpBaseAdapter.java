package com.example.momo.adpater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.momo.base.BaseActivity;

import java.util.List;


public abstract class VpBaseAdapter<T> extends PagerAdapter {
    private List<T> data;
    private BaseActivity context;


    public  VpBaseAdapter(BaseActivity c, List<T> r){

        data=r;
        context=c;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {




        View view = getViewItem(container,position,data);
        container.addView(view);

        return view;
    }

    protected abstract View getViewItem(ViewGroup container, int position, List<T> data);



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


        return  view==object;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);


    }
}

