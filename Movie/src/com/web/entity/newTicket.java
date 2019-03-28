package com.web.entity;

import java.lang.reflect.Array;

/**
 * Created by Hongb on 2018/12/18.
 */
public class newTicket {


    private boolean source;  //来源
    private long    user_id;  //用户id
    private long    schedule_id;
    private int[]  seat_id;
    private Double  price;   //支付价格

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

    public long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(long schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int[] getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int[] seat_id) {
        this.seat_id = seat_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
