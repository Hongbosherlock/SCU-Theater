package com.web.mapper;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.Movie;
import com.web.entity.User;
import com.web.utils.fastJsonUtil;
import org.springframework.ui.ModelMap;

import java.util.List;

import static com.web.controller.client.doPost;

/**
 * Created by Hongb on 2018/12/7.
 */
public class UserMapper {

    private com.web.utils.fastJsonUtil fastJsonUtil=new fastJsonUtil();

    public User getById(String res, JSONObject jsonObject) throws Exception {
        System.out.println("进入mapper");
        String jsonString = doPost(res,jsonObject);
       User user = fastJsonUtil.parseObject(jsonString,User.class);
//jsonstring转class

        return user;

    }

    public User getByMobile(String res, JSONObject jsonObject) throws Exception {
        System.out.println("进入mapper");
        String jsonString = doPost(res,jsonObject);
        User user = fastJsonUtil.parseObject(jsonString,User.class);
//jsonstring转class

        return user;

    }

    public List<User> getAllUser(String res, JSONObject jsonObject) throws Exception {
        ModelMap modelMap = new ModelMap();
        List<User> allUser = null;
        String jsonString = doPost(res,jsonObject);
        allUser = fastJsonUtil.parseArray(jsonString,User.class);

        return allUser;
//jsonstring转对象list
    }

    public boolean updateUser(String res,JSONObject jsonObject) throws Exception {

        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }

    public boolean addUser(String res,JSONObject jsonObject) throws Exception {

//        Movie movie = new Movie();
        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }

    public ModelMap login(String res,JSONObject jsonObject) throws Exception {

//        Movie movie = new Movie();
        ModelMap modelMap = new ModelMap();
        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        if (code.equals("0")){
            User user = fastJsonUtil.parseObject(jsonString,User.class);
            modelMap.addAttribute("flag",true);
            modelMap.addAttribute("user",user);
            return modelMap;
        }
        modelMap.addAttribute("flag",false);
        return modelMap;

    }

}
