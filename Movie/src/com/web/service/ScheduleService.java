package com.web.service;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.Schedule;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface ScheduleService {
    //根据id获取schedule信息
    public JSONObject getScheduleById(long movieId);
    // 获取所有schedule
    public List<Schedule> getAllSchedule() throws Exception;
    public JSONObject  getScheduleList() throws Exception;
    //更新schedule信息
    public boolean updateSchedule(Schedule schedule);
    //添加schedule信息
    public boolean addSchedule(Schedule schedule);
    //删除schedule信息
    public boolean deleteSchedule(long scheduleId);
}
