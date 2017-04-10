package platform.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能：此类用来实现一些基本的httpclient的请求,完成调用
 * 作者： yangxun
 * 时间： 2014/10/20.
 */
public class HttpUtils {

    public static String post(String url,Map<String,String> params){
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        if(null!=params){
            for(String key : params.keySet()){
                formparams.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity, "UTF-8");
                    return responseBody;
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }
    public static String post(String url,String body){
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        try {

            if (StringUtils.isNotBlank(body)) {
                httppost.setEntity(new StringEntity(body));
            }
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity, "UTF-8");
                    return responseBody;
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }
    /**
     * 发送 get请求
     */
    public static String get(String url,Map<String,String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            if (null!=params) {
                List p = new ArrayList();
                for(String key : params.keySet()){
                    p.add(StringUtils.join(key,"=",params.get(key)));
                }
                if(p.size()>0){
                    url+=url.contains("?")?'&':"?"+ StringUtils.join(p,'&');
                }
            }
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                     return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发送 get请求
     */
    public static String put(String url,String body) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpPut httpget = new HttpPut(url);
            if (StringUtils.isNotBlank(body)) {
                httpget.setEntity(new StringEntity(body));
            }
            httpget.addHeader("Content-Type","application/json");
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发送 get请求
     */
    public static String delte(String url,Map<String,String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            if (null!=params) {
                List p = new ArrayList();
                for(String key : params.keySet()){
                    p.add(StringUtils.join(key,"=",params.get(key)));
                }
                if(p.size()>0){
                    url+=url.contains("?")?'&':"?"+ StringUtils.join(p,'&');
                }
            }
            // 创建httpget.
            HttpDelete httpget = new HttpDelete(url);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
