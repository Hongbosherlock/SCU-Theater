package com.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.entity.Movie;
import com.web.entity.Schedule;
import com.web.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Hongb on 2018/11/30.
 */
public class fastJsonUtil {

    public String formatJson(String json){

        JSONObject jsonObject = JSON.parseObject(json);
        json = jsonObject.toJSONString();
        return  json;
    }

    // 把JSON文本parse为JavaBean
    public  <T> T parseObject(String jsonStr, Class<T> clazz){


        if (null != jsonStr && !"".equals(jsonStr.trim())) {
            try {
                T t ;
                JSONObject jsonObject = JSON.parseObject(jsonStr);
                JSONObject object;
                object  =jsonObject.getJSONObject("data");
                String jsonString = JSON.toJSONString(object);
                t = JSON.parseObject(jsonString, clazz);
                return  t;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //把JSON文本parse成JavaBean集合
    public  <T> List<T> parseArray(String jsonStr, Class<T> clazz){

        if (null != jsonStr && !"".equals(jsonStr.trim())) {
            try {
                List<T> list ;
                JSONObject jsonObject = JSON.parseObject(jsonStr);
//                JSONObject object;
//                object  =jsonObject.getJSONObject("data");
//                String jsonString = JSON.toJSONString(object);
                String jsonString = jsonObject.getString("data");
//                System.out.println("电影信息："+jsonString);
                list = JSON.parseArray(jsonString, clazz);
                return  list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    //提取code，
    public String getCode(String jsonStr){
        if (null != jsonStr && !"".equals(jsonStr.trim())) {
            try {

                JSONObject jsonObject = JSON.parseObject(jsonStr);
//                System.out.println(jsonObject);
                String code = jsonObject.getString("code");
                return code ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public JSONObject toMovieObject( Movie movie){
        String content;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",movie.getId());
        movie.setId(null);
        JSONObject movieObject = JSON.parseObject(JSON.toJSONString(movie));

        jsonObject.put("data",movieObject);
        content = jsonObject.toJSONString();


        return  jsonObject;
    }

    public JSONObject toScheduleObject(Schedule schedule){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",schedule.getId());
        schedule.setId(null);
        JSONObject movieObject = JSON.parseObject(JSON.toJSONString(schedule));
        jsonObject.put("data",movieObject);
        return  jsonObject;
    }

    public JSONObject toUserObject(User user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
       user.setId(null);
        JSONObject userObject = JSON.parseObject(JSON.toJSONString(user));
        jsonObject.put("data",userObject);
        return  jsonObject;
    }

//    public String getContent(Movie movie){
//        String content;
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//        jsonObject.put("id",movie.getId());
////        movie.setId(null);
//        JSONObject movieObject = JSON.parseObject(JSON.toJSONString(movie));
//        for (Map.Entry<String, Object> entry : movieObject.entrySet()) {//一个entry代表一个属性，key是属性名，value是属性值
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//            JSONObject object =new JSONObject();
//            object.put(entry.getKey(),entry.getValue());
////            jsonArray1.add(Integer.parseInt(entry.getKey()),entry.getValue());
//            jsonArray.add(object);
//        }
//            jsonObject.put("data",jsonArray);
//            content = jsonObject.toJSONString();
//
//
//        return  content;
//    }

//    public String obj2array(Movie movie){
//        String content;
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        movie.setId(null);
//        JSONObject movieObject = JSON.parseObject(JSON.toJSONString(movie));
//        for (Map.Entry<String, Object> entry : movieObject.entrySet()) {//一个entry代表一个属性，key是属性名，value是属性值
////            System.out.println(entry.getKey() + ":" + entry.getValue());
//            JSONObject object =new JSONObject();
//            object.put(entry.getKey(),entry.getValue());
////            jsonArray1.add(Integer.parseInt(entry.getKey()),entry.getValue());
//            jsonArray.add(object);
//        }
//        jsonObject.put("data",jsonArray);
//        content = jsonObject.toJSONString();
//
//
//        return  content;
//    }



 }







