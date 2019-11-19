package com.example.momo.dao.entity;

import java.util.List;

public class BannerInfo {


    /**
     * code :
     * result : {"banner":[{"url":"https://img.zcool.cn/community/010580585a3298a8012060c8f30cd5.jpg@2o.jpg"},{"url":"https://img.zcool.cn/community/01bd605b23f311a80121bbec61f3d7.jpg@2o.jpg"},{"url":"https://img.zcool.cn/community/0193ee585a3298a801219c775a7aae.jpg@1280w_1l_2o_100sh.jpg"},{"url":"https://img.zcool.cn/community/01a1ac595a3fdda8012193a3a2314b.jpg@2o.jpg"},{"url":"https://img.zcool.cn/community/0184d0595a3fd7a8012193a32d024e.jpg@1280w_1l_2o_100sh.jpg"}]}
     */

    private String code;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<BannerBean> banner;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class BannerBean {
            /**
             * url : https://img.zcool.cn/community/010580585a3298a8012060c8f30cd5.jpg@2o.jpg
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
