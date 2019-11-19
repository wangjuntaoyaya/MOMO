package com.example.momo.utils;

import android.content.Context;

import com.example.momo.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil<T  > {


    public static String fromRaw(Context context, int id) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = context.getResources().openRawResource(R.raw.zuifan);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));


        String string = null;

        try {
            while (((string = bufferedReader.readLine()) != null)) {

                stringBuilder.append(string);

            }


        } catch (IOException e) {
            LogUtil.e("IOExceptIOExceptionIOExceptionIOExceptionion"+e.toString());
            e.printStackTrace();
        } finally {
//            bufferedReader = null;
        }

        return stringBuilder.toString();


    }

    public  T jsonToT(String s, Class<T> t) {
        T tt;
        Gson gson = new Gson();
        tt = (T) gson.fromJson(s, t);

        return tt;

    }

}
