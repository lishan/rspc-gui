package platform.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

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
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    public static int SUCCESS=200;
    public static int CREATED =201;
    public static int ACCEPTED =202;
    public static int NO_CONTENT =204;
    public static int FORBIDDEN=403;


    public static HttpRuest post(String url,String body){
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        try {

            if (StringUtils.isNotBlank(body)) {
                httppost.setEntity(new StringEntity(body));
            }
            httppost.addHeader("Content-Type","application/json");
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if(null==entity){
                    return new HttpRuest(statusCode,null);
                }else {
                    return new HttpRuest(statusCode,EntityUtils.toString(entity));
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
    public static HttpRuest get(String url,Map<String,String> params) {
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
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if(null==entity){
                    return new HttpRuest(statusCode,null);
                }else {
                    return new HttpRuest(statusCode,EntityUtils.toString(entity));
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
    public static HttpRuest put(String url,String body) {
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
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if(null==entity){
                    return new HttpRuest(statusCode,null);
                }else {
                    return new HttpRuest(statusCode,EntityUtils.toString(entity));
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
    public static HttpRuest delte(String url,Map<String,String> params) {
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
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if(null==entity){
                    return new HttpRuest(statusCode,null);
                }else {
                    return new HttpRuest(statusCode,EntityUtils.toString(entity));
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
     * 请求返回
     */
    public static class HttpRuest{
        private int statusCode;
        private  String entity;

        public HttpRuest() {
        }

        public HttpRuest(int statusCode, String entity) {
            this.statusCode = statusCode;
            this.entity = entity;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }
    }
}
