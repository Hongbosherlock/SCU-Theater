package com.web.mapper;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.Movie;
import com.web.entity.Ticket;
import com.web.utils.fastJsonUtil;
import org.springframework.ui.ModelMap;

import java.util.List;

import static com.web.controller.client.doPost;

/**
 * Created by Hongb on 2018/11/29.
 */
public class TicketMapper {

    private fastJsonUtil fastJsonUtil=new fastJsonUtil();

    public Ticket getById(String res, JSONObject jsonObject) throws Exception {
        System.out.println("进入mapper");
        String jsonString = doPost(res,jsonObject);
        Ticket ticket =null;
//jsonstring转class

        return ticket;

    }

    public  List<Ticket> getAllTicket(String res,JSONObject jsonObject) throws Exception {
        ModelMap modelMap = new ModelMap();
        List<Ticket> allTicket = null;
        String jsonString = doPost(res,jsonObject);
        allTicket = fastJsonUtil.parseArray(jsonString,Ticket.class);

        return allTicket;
//jsonstring转对象list
    }


    public boolean addTicket(String res,JSONObject jsonObject) throws Exception {

        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }

    public boolean deleteTicket(String res,JSONObject jsonObject) throws Exception {

        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }


}
