package com.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.entity.Seat;
import com.web.entity.Ticket;
import com.web.entity.newTicket;
import com.web.model.Pages;
import com.web.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单信息处理控制器，控制层

 *
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    //根据id获取订单
    @RequestMapping("/getTicketById.do")
    @ResponseBody
    public ModelMap getTicketById(@RequestParam(value = "id") String id) {
        ModelMap modelMap = new ModelMap();
        Ticket ticket = null;
        ticket = ticketService.getTicketById(Long.parseLong(id));
        if (ticket == null) {
            modelMap.addAttribute("flag", false);
        } else {
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("movie", ticket);
        }
        return modelMap;
    }

    //获取所有订单信息
    @RequestMapping("/getAllTicket.do")
    @ResponseBody
    public ModelMap getAllTicket() {
        ModelMap modelMap = new ModelMap();
        ArrayList<Ticket> allTicket = null;
        allTicket = (ArrayList<Ticket>) ticketService.getAllTicket();
        if (allTicket == null) {
            modelMap.addAttribute("flag", false);
        } else {
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("allMovie", allTicket);
        }
        return modelMap;
    }

    //添加订单信息，购买电影票
    @RequestMapping("/addTicket.do")
    @ResponseBody
    public ModelMap addTicket(@RequestBody newTicket newTicket) {
        ModelMap modelMap = new ModelMap();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSON.parseObject(JSON.toJSONString(newTicket)));
        System.out.println(jsonObject);
        if (ticketService.addTicket(jsonObject)){
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }



    //删除订单信息
    @RequestMapping("/deleteTicket.do")
    @ResponseBody
    public ModelMap deleteTicket(@RequestParam(value = "id") String id) {
        ModelMap modelMap = new ModelMap();
        if (ticketService.deleteTicket(Long.parseLong(id))) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/api/showTicket", method = RequestMethod.GET)
    public Pages<Ticket> apiGetShowSchedule(HttpServletRequest request) throws Exception {
        return showTicket(request, "GET");
    }

    @ResponseBody
    @RequestMapping(value = "/api/showTicket", method = RequestMethod.POST)
    public Pages<Ticket> apiPostShowSchedule(HttpServletRequest request) throws Exception {
        return showTicket(request, "POST");
    }

    public Pages<Ticket> showTicket(HttpServletRequest request, String method) throws Exception {

        String limit = request.getParameter("limit");
        String nowPage = request.getParameter("nowPage");
        // 当前页数
        int nowPaged = Integer.parseInt(null == nowPage ? "1" : nowPage);
        // 每页显示页数
        int limitd = Integer.parseInt(null == limit ? "10" : limit);

        Pages<Ticket> pages = new Pages<>();
        //开始分页,参数1为请求第几页,参数2为请求条数
        PageHelper.startPage(nowPaged, limitd);

        //查询结果
        List<Ticket> ticketList = ticketService.getAllTicket();
        //取记录总条数
        PageInfo<Ticket> pageInfo = new PageInfo<>(ticketList);
        int total = (int) pageInfo.getTotal();

        pages.setSuccess(true);
        pages.setMsg("共查询出" + total + "条数据!");
        pages.setRecords(ticketList);
        pages.setTotal(total);
        pages.setStatus(0);

        return pages;
    }


}