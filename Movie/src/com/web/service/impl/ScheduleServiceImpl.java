package com.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.Movie;
import com.web.entity.Schedule;
import com.web.entity.Seat;
import com.web.mapper.ScheduleMapper;
import com.web.service.ScheduleService;
import com.web.utils.fastJsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleMapper scheduleMapper = new ScheduleMapper();
    private com.web.utils.fastJsonUtil fastJsonUtil=new fastJsonUtil();

    //根据id获取schedule信息
    @Override
    public JSONObject getScheduleById(long movieId)
    {

        System.out.println("进入service");
        String res="schedule/get";
        ModelMap modelMap  = new ModelMap();
        JSONObject content,jsonObject = null;
        Movie movie = new Movie();
        movie.setId(movieId);
        content = JSON.parseObject(JSON.toJSONString(movie));
        try {
            jsonObject= scheduleMapper.getById(res,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject ;
    }
    // 获取所有schedule
    @Override
    public List<Schedule> getAllSchedule() throws Exception{
        String res="schedule/listRaw";
        String content = "{ \"id\": 7}";
        JSONObject jsonObject=JSON.parseObject(content);
        List<Schedule> allSchedule =null;
        try {
            allSchedule = scheduleMapper.getAllSchedule(res,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allSchedule;
    }
    @Override
    public JSONObject  getScheduleList() throws Exception{
        String res="schedule/list";
        String content = "{ \"id\": 7}";
        JSONObject jsonObject=JSON.parseObject(content);
        JSONObject jsonObject1 = null;
        try {
            jsonObject1 = scheduleMapper.getScheduleList(res,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject1;


    }


    //更新schedule信息
    @Override
    public boolean updateSchedule(Schedule schedule){
        String res="schedule/update";
        JSONObject content;
//        content = fastJsonUtil.toMovieObject(schedule);
        content = null;
        try {

            if(scheduleMapper.updateSchedule(res,content)){

                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
    //添加schedule信息
    @Override
    public boolean addSchedule(Schedule schedule){
        String res="schedule/create";
        JSONObject content;
        content = fastJsonUtil.toScheduleObject(schedule);
        try {
            if(scheduleMapper.addSchedule(res,content)){
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
    //删除schedule信息
    @Override
    public boolean deleteSchedule(long scheduleId){
        String res="schedule/delete";
        JSONObject content;
        Schedule schedule = new Schedule();
       schedule.setId(scheduleId);
        content = JSON.parseObject(JSON.toJSONString(schedule));
        try {
            if(scheduleMapper.deleteSchedule(res,content)){
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }


}
