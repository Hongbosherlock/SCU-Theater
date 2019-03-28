package com.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.User;
import com.web.mapper.UserMapper;
import com.web.service.UserService;
import com.web.utils.fastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private UserMapper userMapper = new UserMapper();
    private com.web.utils.fastJsonUtil fastJsonUtil=new fastJsonUtil();

    @Override
    public  User getUserById(long userId){
        String res="vip/get";
        JSONObject content;
        User user = new User();
        user.setId(userId);
        content = JSON.parseObject(JSON.toJSONString(user));
        try {
            user =userMapper.getById(res,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public  User getUserByMobile(String mobile){
        String res="vip/get";
        JSONObject content;
        User user = new User();
        user.setMobile(mobile);
        content = JSON.parseObject(JSON.toJSONString(user));
        try {
            user =userMapper.getByMobile(res,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public List<User> getAllUser() {
        String res="vip/list";
        String content = "{ \"id\": 7}";
        JSONObject jsonObject=JSON.parseObject(content);
        List<User> allUser =null;
        try {
            allUser = userMapper.getAllUser(res,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allUser;
    }

    //更新user信息
    @Override
    public boolean update(User user){
        String res="vip/update";
        JSONObject content;
        content = fastJsonUtil.toUserObject(user);
//        System.out.println(content);
        try {
            if(userMapper.updateUser(res,content)){

                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }

    //添加user信息
    @Override
    public boolean addUser(User user){
        String res="vip/create";
        JSONObject content;
        content = (JSONObject) JSON.toJSON(user);
//        System.out.println(content);
        try {
            if(userMapper.addUser(res,content)){
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }


//    //删除user信息
//    @Override
//    public boolean deleteUser(long userId){
//        if(deleteByPrimaryKey(userId)>0){
//            return  true;
//        }
//        return  false;
//
//    }

    @Override
    public User login(User user) throws Exception {
        String res="vip/login";
        JSONObject content;
        content = (JSONObject) JSON.toJSON(user);
//        System.out.println(content);
        ModelMap modelMap = userMapper.login(res,content);
        User user1 = null;
        if((boolean) modelMap.get("flag")){
               user1 = (User) modelMap.get("user");
            }


        return  user1;
    }

}
