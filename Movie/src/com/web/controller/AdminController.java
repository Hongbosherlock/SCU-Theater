package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.nativesandbox.comm.Response;
import com.web.entity.Admin;
import com.web.entity.User;
import com.web.model.BaseResult;
import com.web.model.Pages;
import com.web.service.AdminService;
import com.web.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员信息处理控制器，控制层
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //根据id获取管理员
    @RequestMapping("/getAdminById.do")
    @ResponseBody
    public ModelMap getAdminById(@RequestParam(value = "id") String id) {
        ModelMap modelMap = new ModelMap();
        Admin admin = null;
        admin = adminService.getAdminById(Long.parseLong(id));
        if (admin == null) {
            modelMap.addAttribute("flag", false);
        } else {
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("admin", admin);
        }
        return modelMap;
    }

    //获取所有管理员信息
    @RequestMapping("/getAllAdmin.do")
    @ResponseBody
    public ModelMap getAllAdmin() {
        ModelMap modelMap = new ModelMap();
        ArrayList<Admin> allAdmin = null;
        allAdmin = (ArrayList<Admin>) adminService.getAllAdmin();
        if (allAdmin == null) {
            modelMap.addAttribute("flag", false);
        } else {
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("allAdmin", allAdmin);
        }
        return modelMap;
    }


    //用户注册，添加会员信息,
    @RequestMapping("/addAdmin.do")
    @ResponseBody
    public ModelMap addAdmin(Admin admin ,HttpServletRequest request)  {
        ModelMap modelMap = new ModelMap();
        if (adminService.addAdmin(admin)) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }
//

    //管理员登录
    @RequestMapping("/login.do")
    @ResponseBody
    public ModelMap login(HttpServletRequest request, Admin admin) throws Exception {
        ModelMap modelMap = new ModelMap();
//        String adminPassword= Md5.EncoderByMd5(admin.getPassword());
//        admin.setPassword(adminPassword);
//        System.out.println(admin);
        HttpSession session = request.getSession();
        Admin admin1 =adminService.login(admin);
        if (admin1==null){
            modelMap.addAttribute("flag",false);
            return modelMap;
        }
        session.setAttribute("loginAdmin", admin1); //把用户信息放session里头
        session.setMaxInactiveInterval(1000);
        modelMap.addAttribute("admin",admin1);
        modelMap.addAttribute("flag",true);
        return modelMap;
    }


    @ResponseBody
    @RequestMapping(value = "addAndUpdateAdmin/{type}", method = RequestMethod.POST)
    public BaseResult addAndUpdateAdmin(Admin admin, @PathVariable String type, HttpServletRequest request) throws Exception {
        boolean success = true,flag=false;
        String msg = "添加成功!";
        if ("add".equals(type)) {
//            movie.setCreateTime(new Date());
//            movie.setUpdateTime(new Date());
            flag = adminService.addAdmin(admin);
        }
//        else if ("update".equals(type)) {
////            movie.setUpdateTime(new Date());
//
//        }

        BaseResult result = new BaseResult();
        if ("add".equals(type) && !flag) {
            success = false;
            msg = "添加失败!";
        }

        result.setSuccess(success);
        result.setMsg(msg);

        return result;
    }



    @ResponseBody
    @RequestMapping(value = "/api/showAdmin", method = RequestMethod.GET)
    public Pages<Admin> apiGetShowUser(HttpServletRequest request) throws Exception {
        return showAdmin(request, "GET");
    }

    @ResponseBody
    @RequestMapping(value = "/api/showAdmin", method = RequestMethod.POST)
    public Pages<Admin> apiPostShowUser(HttpServletRequest request) throws Exception {
        return showAdmin(request, "POST");
    }

    public Pages<Admin> showAdmin(HttpServletRequest request, String method) throws Exception {
        System.out.println("查询所有管理员信息");
        String limit = request.getParameter("limit");
        String nowPage = request.getParameter("nowPage");

        // 当前页数
        int nowPaged = Integer.parseInt(null == nowPage ? "1" : nowPage);
        // 每页显示页数
        int limitd = Integer.parseInt(null == limit ? "10" : limit);

        Pages<Admin> pages = new Pages<>();
        //开始分页,参数1为请求第几页,参数2为请求条数
        PageHelper.startPage(nowPaged, limitd);

        //查询结果
        List<Admin> adminList = adminService.getAllAdmin();
        //取记录总条数
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        int total = (int) pageInfo.getTotal();

        pages.setSuccess(true);
        pages.setMsg("共查询出" + total + "条数据!");
        pages.setRecords(adminList);
        pages.setTotal(total);
        pages.setStatus(0);

        return pages;
    }


}