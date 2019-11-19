package com.example.momo.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.momo.base.BaseActivity;

public class ViewUtils {


    public static  View  getInflaterView(BaseActivity b, int layoutID, ViewGroup viewGroup){
      View view=  LayoutInflater.from(b).inflate(layoutID,viewGroup,false);


        return view;






    }
 }
