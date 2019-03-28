package com.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

/**
 * Created by Hongb on 2018/12/6.
 */
public class client {
    public String getSource(String url) {
        String html = new String();
        HttpGet httpget = new HttpGet(url);     //创建Http请求实例，URL 如：https://cd.lianjia.com/
        // 模拟浏览器，避免被服务器拒绝，返回返回403 forbidden的错误信息
        httpget.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");

        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();   // 使用默认的HttpClient
        try {
            response = httpclient.execute(httpget);
            if (response.getStatusLine().getStatusCode() == 200) {     // 返回 200 表示成功
                html = EntityUtils.toString(response.getEntity(), "utf-8");     // 获取服务器响应实体的内容
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return html;
    }


    public static String doPost(String url,JSONObject json){
        String getUrl = "http://scuplus.com/api/";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(getUrl+url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.addHeader("Content-Type","application/json");

        System.out.println(json);
        String result = null;
                 try {
                         StringEntity s = new StringEntity(json.toJSONString(),"UTF-8");
                         s.setContentEncoding("UTF-8");
                         s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                             "application/json"));
                         s.setContentType("application/json");
                         s.setContentType("application/json");
//                         System.out.println("发送的："+s);
                         post.setEntity(s);
                     CloseableHttpResponse res = client.execute(post);
                     if(res.getStatusLine().getStatusCode() == 200){
                                 HttpEntity entity = res.getEntity();
                                  result = EntityUtils.toString(res.getEntity(),"UTF-8");// 返回json格式：
                                  client.close();
//                                  System.out.println(result);
                                  result = JSON.toJSONString(JSON.parseObject(result));
                             }
                     } catch (Exception e) {
                        throw new RuntimeException(e);
                     }
                return result;
            }
}
