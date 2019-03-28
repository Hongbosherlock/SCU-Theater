package com.web.service;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.Seat;
import com.web.entity.Ticket;

import java.util.List;

public interface TicketService {
    //根据id获取ticket信息
    public Ticket getTicketById(long ticketId);
    // 获取所有ticket
    public List<Ticket> getAllTicket();

    //添加ticket信息
    public boolean addTicket(JSONObject jsonObject);

    //删除ticket信息
    public boolean deleteTicket(long ticketId);


}
//