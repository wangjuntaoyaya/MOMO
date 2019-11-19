package com.example.momo.network.api;

import com.example.momo.dao.entity.UpLoadResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface UpLoadSerive {
    //http://up.qiniu.com
//    {
//        "scope": "",
//            "deadline": 1500020,
//            "returnBody": {
//        "size": "$(fsize)",
//                "w": "$(imageInfo.width)",
//                "name": "$(fname)",
//                "hash": "$(etag)"
//    }
//
//    }
//    @PartMap() Map<String, RequestBody> partMap,


    @Multipart
    @POST("bucket")
    Observable<UpLoadResult> upLoadImage(@Part("action") RequestBody action,@Part("up_token")RequestBody body, @Part("file") MultipartBody.Part file);
}
