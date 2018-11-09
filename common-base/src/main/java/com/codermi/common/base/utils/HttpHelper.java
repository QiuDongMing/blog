package com.codermi.common.base.utils;
import com.alibaba.fastjson.JSON;
import com.codermi.common.base.support.HttpClient;
import com.google.common.collect.Maps;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qiudm
 * @date 2018/11/7 17:45
 * @desc
 */
public class HttpHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

    public static String get(String url, Map<String, String> params) {
        if (params == null) {
            params = Maps.newHashMap();
        }
        StringBuilder sb = new StringBuilder(url);
        int i = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (i == 0) {
                if (url.contains("?")) {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                } else {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                }
            } else {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        url = sb.toString();

        HttpGet httpGet = null;
        try {
            HttpClient httpClient = HttpClient.getHttpClient(null);
            httpGet = new HttpGet(url);
            httpGet.setConfig(httpClient.getRequestConfig());
            return httpClient.execute(httpGet);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
        }
        return null;
    }


    /**
     * 发送post的json请求
     * @param url
     * @param object
     * @return
     */
    public static String postJSON(String url, Object object) {
        HttpPost httpPost = null;
        try {
            HttpClient httpClient = HttpClient.getHttpClient(null);
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(object), "utf-8");
            stringEntity.setContentEncoding("utf-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            httpPost.setConfig(httpClient.getRequestConfig());
            return httpClient.execute(httpPost);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
        }
        return null;
    }


    /**
     * 发起post请求
     */
    public static String post(String url, Map<String, String> paramMap) {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return post(url, formParams);
    }


    public static String post(String url, List<NameValuePair> formParams) {
        HttpPost httpPost = null;
        String respContent = null;
        try {
            HttpClient httpClient = HttpClient.getHttpClient(null);
            httpPost = new HttpPost(url);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(entity);
            httpPost.setConfig(httpClient.getRequestConfig());
            respContent = httpClient.execute(httpPost);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
        }
        return respContent;
    }













}
