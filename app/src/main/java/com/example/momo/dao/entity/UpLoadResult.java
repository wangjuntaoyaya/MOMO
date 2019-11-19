package com.example.momo.dao.entity;

public class UpLoadResult {

    /**
     * scope :
     * deadline : 1500020
     * returnBody : {"size":"$(fsize)","w":"$(imageInfo.width)","name":"$(fname)","hash":"$(etag)"}
     */

    private String scope;
    private int deadline;
    private ReturnBodyBean returnBody;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public ReturnBodyBean getReturnBody() {
        return returnBody;
    }

    public void setReturnBody(ReturnBodyBean returnBody) {
        this.returnBody = returnBody;
    }

    public static class ReturnBodyBean {
        /**
         * size : $(fsize)
         * w : $(imageInfo.width)
         * name : $(fname)
         * hash : $(etag)
         */

        private String size;
        private String w;
        private String name;
        private String hash;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getW() {
            return w;
        }

        public void setW(String w) {
            this.w = w;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}
