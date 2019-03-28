package com.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.Admin;
import com.web.mapper.AdminMapper;
import com.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminMapper adminMapper = new AdminMapper();

    @Override
    public  Admin getAdminById(long adminId){
        System.out.println("进入service");
        String res="admin/get";
        JSONObject content;
        Admin admin = new Admin();
        admin.setId(adminId);
        content = JSON.parseObject(JSON.toJSONString(admin));
        try {
            admin =adminMapper.getById(res,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }

    //添加admin信息
    @Override
    public boolean addAdmin(Admin admin){
        String res="admin/create";
        JSONObject content;
        content = (JSONObject) JSON.toJSON(admin);
//        System.out.println(content);
        try {
            if(adminMapper.addAdmin(res,content)){
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }


    @Override
    public List<Admin> getAllAdmin() {
        String res="admin/list";
        String content = "{ \"id\": 7}";
        JSONObject jsonObject=JSON.parseObject(content);
        List<Admin> allAdmin =null;
        try {
            allAdmin = adminMapper.getAllAdmin(res,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allAdmin;
    }

    @Override
    public Admin login(Admin admin) throws Exception {
        String res="admin/login";
        JSONObject content;
        content = (JSONObject) JSON.toJSON(admin);
//        System.out.println(content);
        ModelMap modelMap = adminMapper.login(res,content);
         Admin admin1= null;
        if((boolean) modelMap.get("flag")){
            admin1 = (Admin) modelMap.get("admin");
        }


        return  admin1;
    }
}
