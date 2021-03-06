package com.jd.study.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author zk.chen
 * @date 2020/8/1
 * http工具类
 */
public class HttpUtils {

    public static final String JSON_TYPE = "application/json";
    public static final String FORM_TYPE = "application/x-www-form-urlencoded";

    /**
     * 请求配置
     */
    private static final RequestConfig config = RequestConfig.custom()
            .setConnectionRequestTimeout(30000)
            .setConnectTimeout(30000)
            .setSocketTimeout(5000)
            .build();

    /**
     * get请求
     *
     * @param url
     * @param header
     * @return
     */
    public static String doGet(String url, Map<String, String> header) {
        final HttpClient client = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        if (header != null)
            header.forEach(httpGet::setHeader);

        String result = null;
        try {
            final HttpResponse execute = client.execute(httpGet);
            result = EntityUtils.toString(execute.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * post请求
     *
     * @param url
     * @param header
     * @param param
     * @return
     */
    public static String doPostForm(String url, Map<String, String> header, Map<String, Object> param) {
        final HttpClient httpClient = HttpClients.createDefault();
        final StringBuffer sb = new StringBuffer();
        param.forEach((k, v) -> {
            sb.append(k + "=" + v);
            sb.append("&");
        });
        String params = sb.toString();
        params = params.endsWith("&") ? StringUtils.substringBeforeLast(params, "&") : params;
        HttpPost httpPost = new HttpPost(url + "?" + params);
        if (header != null)
            header.forEach(httpPost::setHeader);

        String result = null;
        try {
            result = EntityUtils.toString(httpClient.execute(httpPost).getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * post请求
     *
     * @param url
     * @param header
     * @param param
     * @param contentType
     * @return
     */
    public static String doPost(String url, Map<String, String> header, String param, String contentType) {
        final HttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost(url);
        if (header != null)
            header.forEach(httpPost::setHeader);

        final StringEntity entity = new StringEntity(param, Consts.UTF_8);
        entity.setContentType(contentType);
        httpPost.setEntity(entity);
        httpPost.setConfig(config);

        String result = null;
        try {
            result = EntityUtils.toString(client.execute(httpPost).getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
