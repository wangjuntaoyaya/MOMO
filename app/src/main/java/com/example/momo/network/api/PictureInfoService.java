package com.example.momo.network.api;

import com.example.momo.dao.entity.ImageEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface PictureInfoService {

       @GET("pic/{page}/1.txt")
    Observable<ImageEntity>  getImageUrls(@Path("page") int page);
}
