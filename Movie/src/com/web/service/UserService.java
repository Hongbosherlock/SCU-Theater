package com.web.service;

import com.web.entity.User;

import java.util.List;

public interface  UserService {
    //根据id获取user信息
    public User getUserById(long userId);
    public User getUserByMobile(String mobile);
    // 获取所有user
    public List<User> getAllUser();

    //更新user信息
    public boolean update(User user);
    //添加user信息
    public boolean addUser(User user);
    //删除user信息
//    public boolean deleteUser(long userId);

    public User login(User user) throws Exception;
}


