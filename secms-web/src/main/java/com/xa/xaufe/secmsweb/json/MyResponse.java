package com.xa.xaufe.secmsweb.json;

/**
 * 所有控制层要返回的json数据封装
 * @param <T>
 */

public class MyResponse<T> {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;     // 元数据
    private T data;   // 响应内容

    public MyResponse success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public MyResponse success(T data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public MyResponse failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public MyResponse failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }

}
