package com.example.momo.network.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import rx.Observable;

public interface ViewdoDownloadSerive {

    @Streaming
    @GET("035.mp4")
    Observable<ResponseBody> downloadImage();


}
