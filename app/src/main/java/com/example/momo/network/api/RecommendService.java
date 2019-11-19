package com.example.momo.network.api;

import com.example.momo.dao.entity.BannerInfo;
import com.example.momo.dao.entity.News;
import com.example.momo.dao.entity.RecomendsInfo;
import com.example.momo.dao.entity.RelativeVideoInfo;
import com.example.momo.dao.entity.ZhuiFanInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RecommendService  {



        @GET("?key=329b96fdef45c499aaf1986de4b69898&num=20&page=1")
        Observable <News> listRepos(@Query("page") int page);



    @GET("banner.json")
    Observable <BannerInfo> getBannerInfo();

    @GET("dm01.json")

    Observable <RecomendsInfo> getVedioInfo();

    @GET("av/relation.json")

    Observable <RelativeVideoInfo> getRelativeVideoIndo();

    @GET("av/zhuifan.json")
    Observable <ZhuiFanInfo> getFanjuInfo();




}
