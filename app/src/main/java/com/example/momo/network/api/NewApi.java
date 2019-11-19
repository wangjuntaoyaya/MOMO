package com.example.momo.network.api;

import com.example.momo.dao.entity.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

   interface NewsApi {
        @GET("?key=329b96fdef45c499aaf1986de4b69898&num=20&page=1")
        Call<News> listRepos(@Query("page") int page);


    }


