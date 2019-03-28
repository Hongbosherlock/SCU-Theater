package com.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.entity.Schedule;
import com.web.entity.Seat;
import com.web.model.BaseResult;
import com.web.model.Pages;
import com.web.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 电影场次处理控制器，控制层

 *
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    //根据电影id获取场次
    @RequestMapping("/getScheduleById.do")
    @ResponseBody
    public ModelMap getScheduleById(@RequestParam(value = "id") String id) {
        ModelMap modelMap = new ModelMap();
        Schedule schedule = null;

        JSONObject jsonObject= scheduleService.getScheduleById(Long.parseLong(id));
//        System.out.println(jsonObject);
        if (jsonObject == null) {
            modelMap.addAttribute("flag", false);
        } else {

            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("schedule", jsonObject);

        }
        return modelMap;
    }

    //获取所有场次信息
    @RequestMapping("/getAllSchedule.do")
    @ResponseBody
    public ModelMap getAllSchedule() throws Exception {
        ModelMap modelMap = new ModelMap();
//        ArrayList<Schedule> allSchedule = null;
//        allSchedule = (ArrayList<Schedule>) scheduleService.getAllSchedule();
        JSONObject jsonObject = scheduleService.getScheduleList();
        if (jsonObject == null) {
            modelMap.addAttribute("flag", false);
        } else {
//            System.out.println(jsonObject);
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("allSchedule", jsonObject);
        }
        return modelMap;
    }


    //添加场次信息
    @RequestMapping("/addSchedule.do")
    @ResponseBody
    public ModelMap addSchedule(Schedule schedule) {
        ModelMap modelMap = new ModelMap();
        if (scheduleService.addSchedule(schedule)) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "addAndUpdateSchedule/{type}", method = RequestMethod.POST)
    public BaseResult addAndUpdateSchedule(Schedule schedule, @PathVariable String type, HttpServletRequest request) throws Exception {
        boolean success = true,flag=false;
        String msg = "添加成功!";
        System.out.println(schedule);
        if ("add".equals(type)) {

//            movie.setUpdateTime(new Date());
            flag = scheduleService.addSchedule(schedule);
        } else if ("update".equals(type)) {
//            movie.setUpdateTime(new Date());
            flag = scheduleService.updateSchedule(schedule);
        }

        BaseResult result = new BaseResult();
        if ("add".equals(type) && flag==false) {
            success = false;
            msg = "添加失败!";
        } else if ("update".equals(type) && flag==false) {
            success = false;
            msg = "更新失败!";
        } else if ("update".equals(type) && flag==true) {
            success = true;
            msg = "更新成功!";
        }

        result.setSuccess(success);
        result.setMsg(msg);

        return result;
    }



    //删除场次信息
    @RequestMapping("/deleteSchedule.do")
    @ResponseBody
    public ModelMap deleteSchedule(@RequestParam(value = "id") String id) {
        ModelMap modelMap = new ModelMap();
        if (scheduleService.deleteSchedule(Long.parseLong(id))) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/api/showSchedule", method = RequestMethod.GET)
    public Pages<Schedule> apiGetShowSchedule(HttpServletRequest request) throws Exception {
        return showSchedule(request, "GET");
    }

    @ResponseBody
    @RequestMapping(value = "/api/showSchedule", method = RequestMethod.POST)
    public Pages<Schedule> apiPostShowSchedule(HttpServletRequest request) throws Exception {
        return showSchedule(request, "POST");
    }

    public Pages<Schedule> showSchedule(HttpServletRequest request, String method) throws Exception {

        String limit = request.getParameter("limit");
        String nowPage = request.getParameter("nowPage");
        // 当前页数
        int nowPaged = Integer.parseInt(null == nowPage ? "1" : nowPage);
        // 每页显示页数
        int limitd = Integer.parseInt(null == limit ? "10" : limit);

        Pages<Schedule> pages = new Pages<>();
        //开始分页,参数1为请求第几页,参数2为请求条数
        PageHelper.startPage(nowPaged, limitd);

        //查询结果
        List<Schedule> scheduleList = scheduleService.getAllSchedule();
        //取记录总条数
        PageInfo<Schedule> pageInfo = new PageInfo<>(scheduleList);
        int total = (int) pageInfo.getTotal();

        pages.setSuccess(true);
        pages.setMsg("共查询出" + total + "条数据!");
        pages.setRecords(scheduleList);
        pages.setTotal(total);
        pages.setStatus(0);

        return pages;
    }
}