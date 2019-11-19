package com.example.momo.dao.entity;

import java.util.List;

public class RelativeVideoInfo {

    /**
     * code : 200
     * result : {"relation":[{"image_url":"https://i2.hdslb.com/bfs/archive/cd6cc62a382535b81e482c29b71b7c4be3fc98b6.jpg","title":"还原街头铁板鱿鱼，大sao烤四斤吃到爽，最后一盆面垫底，过瘾了","up_name":"徐大sao","watch":"50.0万","danmu":"3500","video_url":""},{"image_url":"https://i2.hdslb.com/bfs/archive/36f8209c9d0acab79ece85b83a76acb34cd41104.jpg","title":"假期在家吃剩菜，真香！米家微波炉【值不值得买第376期】","up_name":"TESTV官方频道","watch":"30万","danmu":"2025","video_url":""},{"image_url":"https://i2.hdslb.com/bfs/archive/bf5b6dfe178d8ceb8a5eea5865945d759b449d24.jpg","title":"【烤鸡架】北京超火的网红烤鸡架？肉多料足，一手啤酒一手鸡架爽到爆！","up_name":"吃货请闭眼","watch":"10万","danmu":"2525","video_url":""},{"image_url":"https://i1.hdslb.com/bfs/archive/87210d142577b72525204e55b6b0ae9b30fdbb68.jpg","title":"胖妹做3斤东坡肉，手抓大坨肉直接啃，肥而不腻，吃得满嘴油，爽","up_name":"陈说美食","watch":"3.0万","danmu":"225","video_url":""},{"image_url":"https://i1.hdslb.com/bfs/archive/02cd118f3169fd3c52bbf545721f3098ca5a9949.jpg","title":"小哥哥在美国海滩用500元大龙虾恶搞老外，最后用喷火枪烤了吃了，还做了龙虾粥","up_name":"JoJo20311","watch":"20","danmu":"20","video_url":""},{"image_url":"https://i0.hdslb.com/bfs/archive/04460e2a058d37d22fd396c09c68e81a16b1eacf.jpg","title":"北京一大傻吃饭点龙虾，8000元一顿的法餐究竟什么样？","up_name":"大祥哥来了","watch":"130万","danmu":"2925","video_url":""},{"image_url":"https://i1.hdslb.com/bfs/archive/7facc446773ae1025a77c4c56dfcb589c5b50fc4.jpg","title":"【曼食慢语】这谁能受的了，被一个馒头可爱到了！想吃！","up_name":"曼食慢语","watch":"80万","danmu":"4025","video_url":""},{"image_url":"https://i1.hdslb.com/bfs/archive/527c8cd7036f4484241c9bbde0181e3edc14f0e1.jpg","title":"超软的蛋糕枕头，一秒就能抓住你的胃","up_name":"老丁头料理","watch":"20万","danmu":"325","video_url":""},{"image_url":"https://i0.hdslb.com/bfs/archive/aa9a3efe5791cabb40fffc1c9ef9475ae795f3a4.jpg","title":"厨师长教你：\u201c酱油炒饭\u201d的家常做法，里面满满的小技巧，很受用","up_name":"美食作家王刚R","watch":"89.0万","danmu":"2059","video_url":""}]}
     * erro : 0
     */

    private int code;
    private ResultBean result;
    private String erro;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public static class ResultBean {
        private List<RelationBean> relation;

        public List<RelationBean> getRelation() {
            return relation;
        }

        public void setRelation(List<RelationBean> relation) {
            this.relation = relation;
        }

        public static class RelationBean {
            /**
             * image_url : https://i2.hdslb.com/bfs/archive/cd6cc62a382535b81e482c29b71b7c4be3fc98b6.jpg
             * title : 还原街头铁板鱿鱼，大sao烤四斤吃到爽，最后一盆面垫底，过瘾了
             * up_name : 徐大sao
             * watch : 50.0万
             * danmu : 3500
             * video_url :
             */

            private String image_url;
            private String title;
            private String up_name;
            private String watch;
            private String danmu;
            private String video_url;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUp_name() {
                return up_name;
            }

            public void setUp_name(String up_name) {
                this.up_name = up_name;
            }

            public String getWatch() {
                return watch;
            }

            public void setWatch(String watch) {
                this.watch = watch;
            }

            public String getDanmu() {
                return danmu;
            }

            public void setDanmu(String danmu) {
                this.danmu = danmu;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }
        }
    }
}
