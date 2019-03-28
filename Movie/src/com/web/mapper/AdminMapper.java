package com.web.mapper;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.Admin;
import com.web.utils.fastJsonUtil;
import org.springframework.ui.ModelMap;

import java.util.List;

import static com.web.controller.client.doPost;

/**
 * Created by Hongb on 2018/12/10.
 */
public class AdminMapper {
    private com.web.utils.fastJsonUtil fastJsonUtil=new fastJsonUtil();

    public Admin getById(String res, JSONObject jsonObject) throws Exception {
        System.out.println("进入mapper");
        String jsonString = doPost(res,jsonObject);
        Admin admin = fastJsonUtil.parseObject(jsonString,Admin.class);
//jsonstring转class

        return admin;

    }

    public List<Admin> getAllAdmin(String res, JSONObject jsonObject) throws Exception {
        ModelMap modelMap = new ModelMap();
        List<Admin> allAdmin = null;
        String jsonString = doPost(res,jsonObject);
        allAdmin = fastJsonUtil.parseArray(jsonString,Admin.class);

        return allAdmin;
//jsonstring转对象list
    }



    public boolean addAdmin(String res,JSONObject jsonObject) throws Exception {

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
            Admin admin= fastJsonUtil.parseObject(jsonString,Admin.class);
            modelMap.addAttribute("flag",true);
            modelMap.addAttribute("admin",admin);
            return modelMap;
        }
        modelMap.addAttribute("flag",false);
        return modelMap;

    }
}
