package com.example.momo.dao.entity;

import java.util.List;

public class News {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2019-09-22 00:00","title":"王毅：中国必须统一 也终将统一","description":"军事要闻","picUrl":"https://img0.utuku.china.com/300x0/mili/20190922/3cd3b3f1-81a4-425a-b5a4-59d82df1e381.jpg","url":"https://military.china.com/important/11132797/20190922/37086398.html"},{"ctime":"2019-09-22 11:51","title":"古装小姐姐街拍：小姐姐穿汉服真美啊，是个温柔的女子，没错了","description":"新浪汉服","picUrl":"http://n.sinaimg.cn/sinacn20112/200/w640h360/20190922/6d24-iewtena3732200.jpg","url":"https://k.sina.com.cn/article_6067221237_m169a272f503300ht2l.html?from=beauty"},{"ctime":"2019-09-21 00:00","title":"美方公布三份关税排除清单，朝相向而行再迈一步","description":"军事要闻","picUrl":"https://img0.utuku.china.com/300x0/mili/20190921/d4d83189-be08-40f6-b12d-4a6cef40434a.jpg","url":"https://military.china.com/important/11132797/20190921/37084246.html"},{"ctime":"2019-09-21 00:00","title":"英政府对美国资本出手了","description":"军事要闻","picUrl":"https://img1.utuku.china.com/300x0/mili/20190921/ed57c0b4-8ae9-414a-bcbb-662b78c60ecd.jpg","url":"https://military.china.com/important/11132797/20190921/37084354.html"},{"ctime":"2019-09-21 00:00","title":"美国制裁伊朗，俄罗斯抛\u201c橄榄枝\u201d","description":"军事要闻","picUrl":"https://img1.utuku.china.com/300x0/mili/20190921/6230cc91-3da8-4886-b75e-fbe4f9175473.jpg","url":"https://military.china.com/important/11132797/20190921/37084773.html"},{"ctime":"2019-09-22 11:04","title":"姚晨五岁儿子学英语 猫咪一旁\u201c陪读\u201d显温馨","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/36066332c60d40e9a6fd22d1e927bd6e.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/11/EPM36OJD00038FO9.html"},{"ctime":"2019-09-22 11:21","title":"何超仪好莱坞电影演女军阀 跟男主角有藕断丝连","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/10a6e4cdb33145e889f47c7cf253c625.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/11/EPM45NUS00038FO9.html"},{"ctime":"2019-09-22 11:26","title":"曾国祥纹身戒指代表婚期 王敏奕:给我梦想婚礼","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/cff2c567b84e4afa961ef69cb08ca8d3.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/11/EPM4F7OJ00038FO9.html"},{"ctime":"2019-09-22 11:27","title":"向佐意大利举行婚礼 甜喊郭碧婷\u201c小向太\u201d","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/dce4971051644c7391f12129068f2e55.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/11/EPM4HKVK00038FO9.html"},{"ctime":"2019-09-22 11:55","title":"郭碧婷爸爸逗趣回应向佐表白：我你不爱没关系","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/8fccd754444b48cc9553752f43b6988c.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/11/EPM64D2U00038FO9.html"},{"ctime":"2019-09-22 11:38","title":"谷歌实现量子霸权？3分20秒解决超算要算1万年的","description":"网易互联网","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/f137d9512eb24c608c86c3126503aedc.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0922/11/EPM55UMB00097U7R.html"},{"ctime":"2019-09-22 09:57","title":"台湾政论节目：大陆狮子骨瘦如柴是因人吃不饱","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/3fd8c9a7041d456795690922aaca92e2.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/09/EPLVC1OL00038FO9.html"},{"ctime":"2019-09-22 09:57","title":"蕾哈娜晒照引歌手前男友留言 曾对她使用暴力","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/b24211c6b8ba4cc99ee6d6154fe230a4.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/09/EPLVDER700038FO9.html"},{"ctime":"2019-09-22 09:58","title":"日本明星洼田正孝水川麻美宣布结婚 两人因戏生情","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/03403c2f098d450d887bd0b1eb4db91c.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/09/EPLVFB9I00038FO9.html"},{"ctime":"2019-09-22 10:14","title":"马蓉否认怀孕传闻后洗泡泡浴 晒照曝光豪宅浴室装潢","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/6f928e6cd63a4b408970fe3d822de71c.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/10/EPM0BG4U00038FO9.html"},{"ctime":"2019-09-22 10:17","title":"大闹火车站女星刘露发文辩解 斥媒体夸大事实","description":"网易明星","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/511657a110924c3f97607f1db900f43c.png?imageView&thumbnail=130y90","url":"https://ent.163.com/19/0922/10/EPM0HSQA00038FO9.html"},{"ctime":"2019-09-22 00:20","title":"中国基建再出杰作 \u201c最宽长江大桥\u201d横空出世","description":"中华国内","picUrl":"https://img2.utuku.china.com/300x200/news/20190922/cf0e798a-afef-41b0-ae71-743c4c73d7c8.jpg","url":"https://news.china.com/domestic/945/20190922/37085842.html"},{"ctime":"2019-09-22 10:16","title":"荒唐！推特再删4301个中国涉港账号","description":"网易互联网","picUrl":"http://cms-bucket.ws.126.net/2019/09/22/b6bfb02c57524cab997aa157316180e4.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0922/10/EPM0FK6N00097U7R.html"},{"ctime":"2019-09-22 10:34","title":"警报声人们为什么会引起人们注意？科学家揭示：大脑会被刺耳的声音干扰","description":"科学探索","picUrl":"https://static.cnbetacdn.com/thumb/mini/article/2019/0922/8d6dc02e987444e.jpg","url":"http://www.cnbeta.com/articles/science/891997.htm"},{"ctime":"2019-09-22 08:17","title":"为什么越想赚钱，却越赚不到钱？","description":"创业资讯","picUrl":"https://a5img.pncdn.cn/2019/0921/1569079169932.png?x-oss-process=image/resize,m_fixed,w_220,h_120","url":"https://www.admin5.com/article/20190922/925950.shtml"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {

        private boolean isChangeLayout=false;
        public void setChangeLayout(boolean b){
            isChangeLayout=b;
        }
        public boolean getChangeLayout(){
            return  isChangeLayout;
        }
        /**
         * ctime : 2019-09-22 00:00
         * title : 王毅：中国必须统一 也终将统一
         * description : 军事要闻
         * picUrl : https://img0.utuku.china.com/300x0/mili/20190922/3cd3b3f1-81a4-425a-b5a4-59d82df1e381.jpg
         * url : https://military.china.com/important/11132797/20190922/37086398.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
//    Response Body:
//        {
//        "code": 200,
//        "msg": "success",
//        "newslist": [
//        {
//        "ctime": "2019-09-22 00:00",
//        "title": "王毅：中国必须统一 也终将统一",
//        "description": "军事要闻",
//        "picUrl": "https://img0.utuku.china.com/300x0/mili/20190922/3cd3b3f1-81a4-425a-b5a4-59d82df1e381.jpg",
//        "url": "https://military.china.com/important/11132797/20190922/37086398.html"
//        },
//        {
//        "ctime": "2019-09-22 11:51",
//        "title": "古装小姐姐街拍：小姐姐穿汉服真美啊，是个温柔的女子，没错了",
//        "description": "新浪汉服",
//        "picUrl": "http://n.sinaimg.cn/sinacn20112/200/w640h360/20190922/6d24-iewtena3732200.jpg",
//        "url": "https://k.sina.com.cn/article_6067221237_m169a272f503300ht2l.html?from=beauty"
//        },
//        {
//        "ctime": "2019-09-21 00:00",
//        "title": "美方公布三份关税排除清单，朝相向而行再迈一步",
//        "description": "军事要闻",
//        "picUrl": "https://img0.utuku.china.com/300x0/mili/20190921/d4d83189-be08-40f6-b12d-4a6cef40434a.jpg",
//        "url": "https://military.china.com/important/11132797/20190921/37084246.html"
//        },
//        {
//        "ctime": "2019-09-21 00:00",
//        "title": "英政府对美国资本出手了",
//        "description": "军事要闻",
//        "picUrl": "https://img1.utuku.china.com/300x0/mili/20190921/ed57c0b4-8ae9-414a-bcbb-662b78c60ecd.jpg",
//        "url": "https://military.china.com/important/11132797/20190921/37084354.html"
//        },