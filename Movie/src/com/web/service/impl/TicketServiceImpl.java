package com.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.Seat;
import com.web.entity.Ticket;
import com.web.mapper.TicketMapper;
import com.web.service.TicketService;
import com.web.utils.fastJsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketMapper ticketMapper = new TicketMapper();

    //根据id获取ticket信息
    @Override
    public Ticket getTicketById(long ticketId)
    {
        System.out.println("进入service");
        String res="order/get";
        JSONObject content;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        content = JSON.parseObject(JSON.toJSONString(ticket));
        try {
            ticket = ticketMapper.getById(res,content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticket ;
    }
    // 获取所有ticket
    @Override
    public List<Ticket> getAllTicket(){
        String res="order/list";
        String content = "{ \"id\": 7}";
        JSONObject jsonObject=JSON.parseObject(content);
        List<Ticket> allTicket =null;
        try {
            allTicket =ticketMapper.getAllTicket(res,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allTicket;
    }

    //添加ticket信息
    @Override
    public boolean addTicket(JSONObject jsonObject){
        String res="order/create";

        try {
            if(ticketMapper.addTicket(res,jsonObject)){
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }

    //删除ticket信息
    @Override
    public boolean deleteTicket(long ticketId){
        String res="order/delete";
        JSONObject content;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        content = JSON.parseObject(JSON.toJSONString(ticket));
            try {
                if(ticketMapper.deleteTicket(res,content))
                    return  true;

            } catch (Exception e) {
                e.printStackTrace();
            }

        return  false;
    }



}
