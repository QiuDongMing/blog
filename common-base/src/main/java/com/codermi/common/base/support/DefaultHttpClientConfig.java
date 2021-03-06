package com.codermi.common.base.support;

public class DefaultHttpClientConfig {

    /**
     * 单位毫秒
     *
     * @return #连接不够用的时候等待超时时间
     */
    public int getConnectionRequestTimeout() {
        return 2000;
    }

    /**
     * 连接超时时间
     */
    public int getConnectionTimeout() {
        return 3000;
    }

    /**
     * 默认请求响应时间
     */
    public int getDefaultSocketTimeout() {
        return 5000;
    }

    /**
     * 每个域名的连接数的最大值(所有域名的最大连接数加起来不超过MaxTotal)
     */
    public int getDefaultMaxPerRoute() {
        return 500;
    }

    /**
     * 整个连接池的最大大小
     */
    public int getMaxTotal() {
        return 5000;
    }

}
