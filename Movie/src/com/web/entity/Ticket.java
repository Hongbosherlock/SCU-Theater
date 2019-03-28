package com.web.entity;

import sun.plugin.javascript.navig.Array;

/**
 * Created by Hongb on 2018/11/8.
 * 订单信息
 */
public class Ticket {
    private Long    id;
    private Long    token;  //取票码
    private long    time;   //时间戳
    private boolean source;  //来源
    private long    user_id;  //用户id

    private int     num;      //座位数
    private Double  price;   //支付价格




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSource() {
        return source;
    }

    public void setSource(boolean source) {
        this.source = source;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
