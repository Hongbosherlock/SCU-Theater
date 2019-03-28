package com.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.web.entity.Movie;
import org.springframework.ui.ModelMap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Hongb on 2018/11/24.
 */

public class getData {

    /**
     * 发送get请求
     * 请求地址
     * @return String
     * @throws Exception
     */
    public static String sendGet(String res) throws Exception {
        String getUrl = "http://scuplus.com/api/";
        HttpURLConnection connection = null;
        InputStream in = null;
        BufferedReader br = null;
        String result = null;
        URL url = new URL(getUrl+res);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.setUseCaches(false);
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64)");
        connection.connect();
        if (connection.getResponseCode() == 200) {
            // 从连接中获取输入流
            in = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp+"\n");
                sbf.append("\r\n");
            }
            //关闭bufferReader和输入流
            br.close();
            in.close();
            // 关闭链接
            connection.disconnect();
            result = sbf.toString();

        }
        return result;
    }

    public static String sentPost(String res,JSONObject jsonObject) throws Exception{
        String getUrl = "http://scuplus.com/api/";
        HttpURLConnection connection = null;
        InputStream in = null;
        BufferedReader br = null;
        String result = null;
        URL url = new URL(getUrl+res);

        connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
//        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("Connection", "Keep-Alive");
        //转换为字节数组
        byte[] data = (jsonObject.toString()).getBytes();
        // 设置文件长度
        connection.setRequestProperty("Content-Length", String.valueOf(data.length));
        connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64)");
        connection.connect();


        OutputStream out = new DataOutputStream(connection.getOutputStream()) ;
        // 写入请求的字符串
        out.write(jsonObject.toString().getBytes());
        out.flush();
        out.close();

//        System.out.println(connection.getResponseCode());
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("连接成功");
            // 从连接中获取输入流
            in = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
            }
            //关闭bufferReader和输入流
            br.close();
            in.close();
            // 关闭链接
            connection.disconnect();
            result = sbf.toString();

        }
        return result;

    }






    public static void main(String[] args) throws Exception {


    }
}
