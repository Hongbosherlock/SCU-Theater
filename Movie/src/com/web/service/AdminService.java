package com.web.service;

import com.web.entity.Admin;
import com.web.entity.User;

import java.util.List;

public interface AdminService {
    //根据id获取admin信息
    public Admin getAdminById(long adminId);
    //添加admin信息
    public boolean addAdmin(Admin admin);

    // 获取所有admin
    public List<Admin> getAllAdmin();

    public Admin login(Admin admin) throws Exception;

    }
