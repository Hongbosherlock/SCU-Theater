package com.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Hongb on 2018/11/8.
 * 会员信息
 */
public class User {

    //用户ID
    private Long id;
    //用户名
    private String name;


    //密码
    private String password;
    //联系方式
    private String mobile;
    private Long points; //积分
    private Double discount; //折扣

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("createTime")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("updateTime")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User(Long id, String userName, String password, String mobile, Long points, Double discount) {
        super();
        this.id = id;
        this.name = userName;
        this.password = password;
        this.mobile = mobile;
        this.points = points;
        this.discount  = discount;

    }
    @Override
    public String toString() {
        return "Users [id=" + id + ", userName=" + name + ", password=" + password + ", mobile=" + mobile
                +  "]";
    }
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }




}


