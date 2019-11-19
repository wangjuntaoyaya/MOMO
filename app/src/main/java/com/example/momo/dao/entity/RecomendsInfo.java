package com.example.momo.dao.entity;

import java.io.Serializable;
import java.util.List;

public class RecomendsInfo implements Serializable {


    /**
     * resultcode : 200
     * reason : 哔哩哔哩干杯
     * result : {"users":[{"name":"橙子味","image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569870762965&di=1b027747657bdd023f92b3dbee36b81d&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171204%2F83939e18fcb04b74b651d5499986522b.jpeg","thumbnail":"https://i1.hdslb.com/bfs/archive/0bcab665a9c03f2cfbf7276ea8423d53052e53aa.jpg","title":"【明日方舟手书】Be Affected【学园大香蕉ed再现】","time":"2019-07-07 10:15:04","tag":"明日方舟","watch":"4.3万","viewtime":"05:30","danmu":"306弹幕","url":"https://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/72/79/101477972/101477972-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908233&gen=playurl&os=wcsu&oi=1032614067&trid=d070d340fa68460aa150a910925a639ch&platform=html5&upsig=1f2130eb95fe291e0d3632e8043dfff9&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"Air Wings","image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569871336260&di=e0618cedfd2537365d9efd5b3145dd49&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F10363663568%2F641.jpg","thumbnail":"https://i1.hdslb.com/bfs/archive/e7b7eba6bea5bcc2aa3d77812ccd2844ec42ed03.jpg","title":"我的萝莎莉娅和莉莉娅居然对我说出这种话...","time":"2019-06-26 17:43:10","tag":"动漫恶搞","watch":"5625","viewtime":"08:32","danmu":"506弹幕","url":"https://cn-zjwz3-dx-v-06.acgvideo.com/upgcxcode/03/24/99412403/99412403-1-6.mp4?expires=1569908100&platform=html5&ssig=P0JrPGVSmCTWLEj6xyxThg&oi=1032614414&trid=66479cfa5b1944da877dd0f9998dbe4bh&nfc=1&nfb=maPYqpoel5MI3qOUX6YpRA==&mid=0"},{"name":"八重樱","image":"http://5b0988e595225.cdn.sohucs.com/images/20181003/1532823bdbb74073839e167de755efb0.jpeg","thumbnail":"https://i1.hdslb.com/bfs/archive/ccd11f20f307d33f4ea1fe6f9650f51ac7d0c952.jpg","title":"【日语同人音声】不要再玩FPS游戏啦~陪陪我好不好【Miyadi】","time":"2019-09-04 12:47:59","tag":"日语配音","watch":"20万","viewtime":"09:20","danmu":"1000弹幕","url":"https://upos-hz-mirrorcosu.acgvideo.com/upgcxcode/26/62/115466226/115466226-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908358&gen=playurl&os=cosu&oi=1032614414&trid=0d7b3deed65b4c228d865c82c69dae7dh&platform=html5&upsig=3413749839b9e4e042984896c022de5d&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"皮卡丘","image":"http://5b0988e595225.cdn.sohucs.com/images/20171107/42d3276855b0445ab0b19998e1539d37.jpeg","thumbnail":"https://i0.hdslb.com/bfs/archive/a698d40e0bcaec0abaa87fccb199531cef3116d0.jpg","title":"「Cyberangel」\u2014\u2014《崩坏3》印象曲（演唱者：Hanser）","time":"2019-07-05 11:55:10","tag":"原创音乐","watch":"2.6万","viewtime":"03：09","danmu":"5896弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/07/00/101000007/101000007-1-6.mp4?e=ig8euxZM2rNcNbua7WdVtWua7WdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908404&gen=playurl&os=kodou&oi=1032614414&trid=23a6d3e91e6d444f827cf2c1ef694385h&platform=html5&upsig=af8ca00e95aa268c49d91b67305e84ee&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"黑多儿","image":"http://inews.gtimg.com/newsapp_bt/0/10150803793/641.jpg","thumbnail":"https://i0.hdslb.com/bfs/archive/7e51cf64177fd8d9669269e3fbd7fd33cd1ff708.jpg","title":"容身之所（电影《罗小黑战记》印象曲）","time":"2019-09-30 10:00:30","tag":"原创音乐","watch":"2.8万","viewtime":"03:23","danmu":"615弹幕","url":"https://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/72/79/101477972/101477972-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908233&gen=playurl&os=wcsu&oi=1032614067&trid=d070d340fa68460aa150a910925a639ch&platform=html5&upsig=1f2130eb95fe291e0d3632e8043dfff9&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=2"},{"name":"月影Yueing","image":"http://img.mp.itc.cn/upload/20170220/2b30d878fd24418b952fa93511d75342_th.jpeg","thumbnail":"https://i2.hdslb.com/bfs/archive/6f74437091022634dfc0bcd75f0972061e55663b.jpg","title":"适合一个人看的日漫，男主全程操作高能！","time":"2019-08-31 09:05:23","tag":"综合","watch":"77.9万","viewtime":"02:52","danmu":"1200弹幕","url":"https://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/18/45/114594518/114594518-1-6.mp4?e=ig8euxZM2rNcNbTa7WdVtWTa7WdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908588&gen=playurl&os=wcsu&oi=1032614414&trid=762bba139c144b558b65c649e5cea6e7h&platform=html5&upsig=b0a4f27d1e138862325c75ae0e8e665e&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"阿狸才不是受","image":"http://inews.gtimg.com/newsapp_bt/0/10116238688/1000.jpg","thumbnail":"https://i1.hdslb.com/bfs/archive/92e65ec0b180de65f6e22db847e56fdac6104bd3.jpg","title":"十年前的显卡可以玩什么游戏？和2080ti相比差距有多少？","time":"2019-09-27 18:02:06","tag":"数码","watch":"5089","viewtime":"11:18","danmu":"1502弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/05/63/119856305/119856305-1-6.mp4?e=ig8euxZM2rNcNbuBhwdVtWuBhwdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908632&gen=playurl&os=kodou&oi=1032614414&trid=d3991ba860bb44569183378245fc8ac1h&platform=html5&upsig=d831b10ce3a54d17d5bb8ead068d6cfd&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"北海盗","image":"http://inews.gtimg.com/newsapp_bt/0/10126611165/1000.jpg","thumbnail":"https://i1.hdslb.com/bfs/archive/1d4eaa347329e3a1f753d6f2a21b3439c38b0959.jpg","title":"我和我的祖国-三个月，两万公里，特别献礼！","time":"2019-09-30 10:17:48","tag":"VLOG","watch":"195","viewtime":"04:20","danmu":"8","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/32/84/120288432/120288432-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908659&gen=playurl&os=kodou&oi=1032614414&trid=95d99c9408cc4c4ea5bed8832305e8cah&platform=html5&upsig=e45ea5d447befe80e42b12e9c0aaa097&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"呆呆兽","image":"http://inews.gtimg.com/newsapp_bt/0/10118566983/1000.jpg","thumbnail":"https://i1.hdslb.com/bfs/archive/64923a586b287d9aab97d15de7feaaf91c2d3afd.jpg","title":"【油管搬运/UT同人】史诗级的英文配音 | UNDERVERSE - XTRA SCENE 2 [By Jakei]","time":"2018-02-15 03:26:05","tag":"短片","watch":"1.3万","viewtime":"08:26","danmu":"164弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/12/89/31968912/31968912-1-16.mp4?e=ig8euxZM2rNcNbRghwdVhoM1hbdVhwdEto8g5X10ugNcXBB_&uipk=5&nbs=1&deadline=1569908763&gen=playurl&os=kodou&oi=1032614067&trid=f63d12a2018f4db88b67aba73c82ff3ch&platform=html5&upsig=3fff9179afaaa7df988188d4003ed34b&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"假美食up主","image":"https://i0.hdslb.com/bfs/archive/28071fb9458a5d6c5d05f0a57d5c0054e42daa66.jpg","thumbnail":"https://i0.hdslb.com/bfs/archive/28071fb9458a5d6c5d05f0a57d5c0054e42daa66.jpg","title":"一个人去唐人街吃中餐 经理看到我直接打折","time":"2019-09-15 03:26:05","tag":"VLOG","watch":"150万","viewtime":"05:26","danmu":"5000弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/12/89/31968912/31968912-1-16.mp4?e=ig8euxZM2rNcNbRghwdVhoM1hbdVhwdEto8g5X10ugNcXBB_&uipk=5&nbs=1&deadline=1569908763&gen=playurl&os=kodou&oi=1032614067&trid=f63d12a2018f4db88b67aba73c82ff3ch&platform=html5&upsig=3fff9179afaaa7df988188d4003ed34b&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"徐大骚","image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570338669147&di=5322122597c049f47daf17cce56f4879&imgtype=0&src=http%3A%2F%2Fpuui.qpic.cn%2Fqqvideo_ori%2F0%2Fh0859878icz_496_280%2F0","thumbnail":"https://i0.hdslb.com/bfs/archive/8248ddd72753bc28ae3a118c94d1a5e45ba2415f.jpg","title":"深夜撸串，大sao巧遇国外友人，最后大街上吃炸酱面吸田螺，过瘾","time":"2019-09-28 03:26:05","tag":"美食","watch":"130万","viewtime":"05:26","danmu":"2564弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/12/89/31968912/31968912-1-16.mp4?e=ig8euxZM2rNcNbRghwdVhoM1hbdVhwdEto8g5X10ugNcXBB_&uipk=5&nbs=1&deadline=1569908763&gen=playurl&os=kodou&oi=1032614067&trid=f63d12a2018f4db88b67aba73c82ff3ch&platform=html5&upsig=3fff9179afaaa7df988188d4003ed34b&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"柴犬老丸子","image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570338553218&di=5e5ac311d8d6c3d2569e1fda42c0b9de&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171115%2F14eee8353bd54f11896aa34fd8c11361.jpeg","thumbnail":"https://i1.hdslb.com/bfs/archive/d802b4981aa515702d8458cd209b972792d724df.jpg","title":"烤一只脆皮鸡，撕着吃，肉汁喷了一身","time":"2019-09-12 21:30:53","tag":"美食","watch":"48.0万","viewtime":"4.45","danmu":"5000弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/12/89/31968912/31968912-1-16.mp4?e=ig8euxZM2rNcNbRghwdVhoM1hbdVhwdEto8g5X10ugNcXBB_&uipk=5&nbs=1&deadline=1569908763&gen=playurl&os=kodou&oi=1032614067&trid=f63d12a2018f4db88b67aba73c82ff3ch&platform=html5&upsig=3fff9179afaaa7df988188d4003ed34b&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"女胖胖","image":"http://inews.gtimg.com/newsapp_bt/0/10118566983/1000.jpg","thumbnail":"https://i1.hdslb.com/bfs/archive/2e801c3ecdfd88bc7c753d89e5ca4230d9e4c475.jpg","title":"韩国炸鸡店所有的炸鸡都买下来！一口下去汁多皮脆外酥里嫩！","time":"2018-02-15 03:26:05","tag":"短片","watch":"25万","viewtime":"8:26","danmu":"1564弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/12/89/31968912/31968912-1-16.mp4?e=ig8euxZM2rNcNbRghwdVhoM1hbdVhwdEto8g5X10ugNcXBB_&uipk=5&nbs=1&deadline=1569908763&gen=playurl&os=kodou&oi=1032614067&trid=f63d12a2018f4db88b67aba73c82ff3ch&platform=html5&upsig=3fff9179afaaa7df988188d4003ed34b&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"郭杰瑞","image":"http://wx4.sinaimg.cn/thumb300/0068KGZxgy1g6xme6tah0j30u0140e81.jpg","thumbnail":"https://i1.hdslb.com/bfs/archive/06b64a867dec1a3418fd15d37099d347a1cafe8d.jpg","title":"纽约最便宜牛排VS巴菲特天价午餐牛排！这期翻车了","time":"2019-10-03 18:00:28","tag":"生活","watch":"123.8万","viewtime":"04:26","danmu":"1.0万弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/12/89/31968912/31968912-1-16.mp4?e=ig8euxZM2rNcNbRghwdVhoM1hbdVhwdEto8g5X10ugNcXBB_&uipk=5&nbs=1&deadline=1569908763&gen=playurl&os=kodou&oi=1032614067&trid=f63d12a2018f4db88b67aba73c82ff3ch&platform=html5&upsig=3fff9179afaaa7df988188d4003ed34b&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"},{"name":"Amy艾米饭","image":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570338458414&di=c65a29469d52e72f148808589b2c8e14&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Fface%2Fb1023abb70780eaa25d351446347c35a05d77abe.jpg","thumbnail":"https://i0.hdslb.com/bfs/archive/141130004df10e99c19f5e23bec5d2733f883c20.jpg","title":"外国女孩探索苏州！","time":"2019-10-02 19:51:00","tag":"日常","watch":"208","viewtime":"06:26","danmu":"280弹幕","url":"https://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/12/89/31968912/31968912-1-16.mp4?e=ig8euxZM2rNcNbRghwdVhoM1hbdVhwdEto8g5X10ugNcXBB_&uipk=5&nbs=1&deadline=1569908763&gen=playurl&os=kodou&oi=1032614067&trid=f63d12a2018f4db88b67aba73c82ff3ch&platform=html5&upsig=3fff9179afaaa7df988188d4003ed34b&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0"}],"banner":[{"url":"https://ojlty2hua.qnssl.com/image-1570614077850-MDEucG5n.png"},{"url":"https://ojlty2hua.qnssl.com/image-1570614320560-MDIucG5n.png"},{"url":"https://ojlty2hua.qnssl.com/image-1570614432180-MDMucG5n.png"},{"url":"https://ojlty2hua.qnssl.com/image-1570614460519-MDQucG5n.png"},{"url":"https://ojlty2hua.qnssl.com/image-1570614497541-MDUucG5n.png"}]}
     */

    private String resultcode;
    private String reason;
    private ResultBean result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<UsersBean> users;
        private List<BannerBean> banner;

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class UsersBean implements Serializable {
            /**
             * name : 橙子味
             * image : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569870762965&di=1b027747657bdd023f92b3dbee36b81d&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171204%2F83939e18fcb04b74b651d5499986522b.jpeg
             * thumbnail : https://i1.hdslb.com/bfs/archive/0bcab665a9c03f2cfbf7276ea8423d53052e53aa.jpg
             * title : 【明日方舟手书】Be Affected【学园大香蕉ed再现】
             * time : 2019-07-07 10:15:04
             * tag : 明日方舟
             * watch : 4.3万
             * viewtime : 05:30
             * danmu : 306弹幕
             * url : https://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/72/79/101477972/101477972-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&uipk=5&nbs=1&deadline=1569908233&gen=playurl&os=wcsu&oi=1032614067&trid=d070d340fa68460aa150a910925a639ch&platform=html5&upsig=1f2130eb95fe291e0d3632e8043dfff9&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0
             */

            private String name;
            private String image;
            private String thumbnail;
            private String title;
            private String time;
            private String tag;
            private String watch;
            private String viewtime;
            private String danmu;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getWatch() {
                return watch;
            }

            public void setWatch(String watch) {
                this.watch = watch;
            }

            public String getViewtime() {
                return viewtime;
            }

            public void setViewtime(String viewtime) {
                this.viewtime = viewtime;
            }

            public String getDanmu() {
                return danmu;
            }

            public void setDanmu(String danmu) {
                this.danmu = danmu;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class BannerBean {
            /**
             * url : https://ojlty2hua.qnssl.com/image-1570614077850-MDEucG5n.png
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
