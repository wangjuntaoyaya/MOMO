package com.example.momo.constants;

public class MyApi {
    //新闻

    static String BashUri="http://api.tianapi.com/generalnews/";
    static String BashUri_news="http://api.tianapi.com/generalnews/";

    static String NesKey="329b96fdef45c499aaf1986de4b69898";

    public MyApi(){

    }

    public static String getBashUri() {
        return BashUri_news;
    }

    public static String getNesKey() {
        return NesKey;
    }
}
