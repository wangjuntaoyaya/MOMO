package com.example.momo.network.api;

import com.example.momo.constants.MyApi;
import com.example.momo.dao.entity.News;
import com.example.momo.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestNetwork {



            public static   void  obtainNews(int page){

                LogUtil.e("obtainNews");
                Retrofit retrofit=new Retrofit.Builder().baseUrl(MyApi.getBashUri()).addConverterFactory(GsonConverterFactory.create()).build();
                NewsApi service= retrofit.create(NewsApi.class);

                        Call<News> repos = service.listRepos(page);

               repos.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {

                        LogUtil.e((response.body().getNewslist().get(0).getTitle())+"");
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        LogUtil.e("onFailure");



                    }
                });


            }






}
