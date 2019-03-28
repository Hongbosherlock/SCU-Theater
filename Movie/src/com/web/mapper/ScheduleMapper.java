package com.web.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.Schedule;
import com.web.utils.fastJsonUtil;
import org.springframework.ui.ModelMap;

import java.util.List;

import static com.web.controller.client.doPost;

/**
 * Created by Hongb on 2018/12/13.
 */
public class ScheduleMapper {
    private com.web.utils.fastJsonUtil fastJsonUtil=new fastJsonUtil();

    public    JSONObject getById(String res, JSONObject jsonObject) throws Exception {
        System.out.println("进入mapper");
        ModelMap modelMap = null;
        String jsonString = doPost(res,jsonObject);
        JSONObject jsonObject1,jsonObject2=new JSONObject();
        jsonObject1= JSON.parseObject(jsonString);
        jsonObject2.put("data",jsonObject1.getString("data"));
//        Movie movie = fastJsonUtil.parseObject(jsonString,Movie.class);
//jsonstring转class

        return jsonObject2;

    }

    public List<Schedule> getAllSchedule(String res, JSONObject jsonObject) throws Exception {
        List<Schedule> allSchedule = null;
        String jsonString = doPost(res,jsonObject);
        allSchedule = fastJsonUtil.parseArray(jsonString,Schedule.class);

        return allSchedule;
//jsonstring转对象list
    }

    public JSONObject getScheduleList(String res, JSONObject jsonObject) throws Exception {

        String jsonString = doPost(res,jsonObject);
        JSONObject jsonObject1,jsonObject2=new JSONObject();
        jsonObject1= JSON.parseObject(jsonString);
        jsonObject2.put("data",jsonObject1.getString("data"));
//        jsonObject1 = (JSONObject) jsonObject1.remove("message");
//        System.out.println(jsonObject2);
        return jsonObject2;
//jsonstring转对象list
    }

    public boolean updateSchedule(String res,JSONObject jsonObject) throws Exception {


        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }

    public boolean addSchedule(String res,JSONObject jsonObject) throws Exception {

        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }

    public boolean deleteSchedule(String res,JSONObject jsonObject) throws Exception {

//        Movie movie = new Movie();
        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }
}
