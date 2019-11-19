package com.example.momo.dao.entity;

import java.util.List;

public class ImageEntity {

    /**
     * result : 200
     * images : [{"name":"01","url":"https://i0.hdslb.com/bfs/manga-static/776201d365142561233f9f7e2a0e4b53bd7ba5fb.jpg"},{"name":"02","url":"https://i0.hdslb.com/bfs/manga-static/76e07a834deefc390790905db0fe84d1cab000ca.jpg"},{"name":"03","url":"https://i0.hdslb.com/bfs/manga-static/77efeb8a54b96d5191e016260bd98d2c46b58a04.jpg"},{"name":"04","url":"https://i0.hdslb.com/bfs/manga-static/7c4fb47298080963a56acf812e57072aa747bea4.jpg"},{"name":"05","url":"https://i0.hdslb.com/bfs/manga-static/869876094d9ceca4bc1acb10970966a02a95a138.jpg"},{"name":"06","url":"https://i0.hdslb.com/bfs/manga-static/869f7237445f9cc95e599bc58d8bf9f5bdda7de1.jpg"},{"name":"07","url":"https://i0.hdslb.com/bfs/manga-static/8805ae2853807d61de5a31ed454ed726fafd766f.png"},{"name":"08","url":"https://i0.hdslb.com/bfs/manga-static/93f2a93448f94432c78c209642f1a9a4b07c36da.jpg"}]
     */

    private int result;
    private List<ImagesBean> images;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * name : 01
         * url : https://i0.hdslb.com/bfs/manga-static/776201d365142561233f9f7e2a0e4b53bd7ba5fb.jpg
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
