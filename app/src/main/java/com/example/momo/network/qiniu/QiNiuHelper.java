package com.example.momo.network.qiniu;

import com.example.momo.utils.LogUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.util.HashMap;

public class QiNiuHelper {

  String ACCESS_KEY = "KXSJlBSiimusMGLdQNFZTiNAYbK0Mlu6qznIkzhg";
  String SECRET_KEY = "xGM8EPaDJRlr6u607GYT27ZI8JlATfALi97G_znv";
  private UploadManager uploadManager;
  private Auth auth;

  public  void UpLoadeImage(){



  }
  public String getUpToken() {
    return auth.uploadToken("yayane");
  }
  public void upload(){
    LogUtil.e("----------upload------------------------" );

    //要上传的空间
    String bucketname = "yayane";
    //上传到七牛后保存的文件名
    String key = "qjzh.jpg";
    //上传文件的路径
    String FilePath = "/sdcard/03.jpg";

    //密钥配置
    auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    Zone z = Zone.zone2();
    Configuration c = new Configuration(z);
    uploadManager = new UploadManager(c);
    try {
      //调用put方法上传

      Response res = uploadManager.put(FilePath, key, getUpToken());

    } catch (QiniuException e) {
      Response r = e.response;
      // 请求失败时打印的异常的信息

      try {
        //响应的文本信息
        System.out.println(r.bodyString());
      } catch (QiniuException e1) {
        //ignore
      }
    }
  }


public String downLoad(String key){



  //密钥配置
  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
  //构造私有空间的需要生成的下载的链接
  String URL = "http://pz3pg86zw.bkt.clouddn.com/"+key;
  //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
  String downloadRUL = auth.privateDownloadUrl(URL, 3600);
return downloadRUL;

  }




  public void upLoad1(){
    UpLoadeImage();
//    String UploadTokem= "KXSJlBSiimusMGLdQNFZTiNAYbK0Mlu6qznIkzhg:KE9ScXgJHX_MXGAa3eB4tG-37Vk=:eyJzY29wZSI6Im1vbW9uZSIsImRlYWRsaW5lIjoxNTcxMzUwNDM3fQ==";
//    //设置好账号的ACCESS_KEY和SECRET_KEY
//    String ACCESS_KEY = "xGM8EP;aDJRlr6u607GYT27ZI8JlATfALi97G_znv";
//    String SECRET_KEY = "Secret_Key";
//    //要上传的空间
//    String bucketname = "Bucket_Name";
//    //上传到七牛后保存的文件名
//    String key = "my-java.png";
//    //上传文件的路径
//    String FilePath = "/.../...";
//
//    //密钥配置
//    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
//    File file=new File("/storage/emulated/0/splash/233.png");
//    LogUtil.e("file"+file.length());
//
//    RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"),file );
//    MultipartBody.Builder builder = new MultipartBody.Builder()
//            .setType(MultipartBody.FORM);//表单类型
//    requestBody_action=RequestBody.create(MediaType.Parse())
//    builder.addFormDataPart("action","http://upload-z2.qiniup.com");
//    builder.addFormDataPart("token","\n" +
//            "KXSJlBSiimusMGLdQNFZTiNAYbK0Mlu6qznIkzhg:Ql1V8c-Gr0wqS31BLBBdEA7wqz8=:eyJzY29wZSI6InlheWFuZSIsImRlYWRsaW5lIjoxNTcxMzIxMjk5fQ==");
//    builder.addPart(requestBody);
//
////    MultipartBody.Part part = MultipartBody.Part.createFormData("multipartFiles", "2333", requestBody);
//
//    RetrofitHelper.upLoadAPI(ApiConstants.QNIUHN).upLoadImage( builder.build().parts()).subscribeOn(Schedulers.io()).
//  observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UpLoadResult>() {
//                                                        @Override
//                                                        public void onCompleted() {
//                                                          LogUtil.e("onCompleted");
//                                                        }
//
//                                                        @Override
//                                                        public void onError(Throwable e) {
//                                                          LogUtil.e("onNext"+e.toString());
//                                                        }
//
//                                                        @Override
//                                                        public void onNext(UpLoadResult upLoadResult) {
//                                                          LogUtil.e("onNext");
//
//                                                        }
//                                                      }

//    );





  }
  public String Generate(){





//    scope = 'my-bucket:sunflower.jpg'
//    deadline = 1451491200
//    returnBody = '{
//    "name": $(fname),
//            "size": $(fsize),
//            "w": $(imageInfo.width),
//            "h": $(imageInfo.height),
//            "hash": $(etag)
//  }'
  HashMap hashMap=new HashMap();
    HashMap returnBody=new HashMap();
  hashMap.put("scope","");
    hashMap.put("deadline","");
    returnBody.put("name","$(fname)");
    returnBody.put("size","$(fsize)");
    returnBody.put("w","$(imageInfo.width)");
    returnBody.put("hash","$(etag)");
    hashMap.put("returnBody",returnBody );



    Gson gson= new Gson();


return gson.toJson(hashMap);

  }

}
