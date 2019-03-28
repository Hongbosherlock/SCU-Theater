package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.entity.User;
import com.web.model.BaseResult;
import com.web.model.Pages;
import com.web.service.UserService;
import com.web.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员信息处理控制器，控制层
 *
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //根据id获取会员
    @RequestMapping("/getUserById.do")
    @ResponseBody
    public ModelMap getUserById(@RequestParam(value = "id") String id) {
        ModelMap modelMap = new ModelMap();
        User user = null;
        user = userService.getUserById(Long.parseLong(id));
        if (user == null) {
            modelMap.addAttribute("flag", false);
        } else {
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("user", user);
        }
        return modelMap;
    }

    //获取所有会员信息
    @RequestMapping("/getAllUser.do")
    @ResponseBody
    public ModelMap getAllUser() {
        ModelMap modelMap = new ModelMap();
        ArrayList<User> allUser = null;
        allUser = (ArrayList<User>) userService.getAllUser();
        if (allUser == null) {
            modelMap.addAttribute("flag", false);
        } else {
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("allUser", allUser);
        }
        return modelMap;
    }

    //修改会员信息
    @RequestMapping("/updateUser.do")
    @ResponseBody
    public ModelMap updateUser(User user) {
        ModelMap modelMap = new ModelMap();
        if (userService.update(user)) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }

    //用户注册，添加会员信息,
    @RequestMapping("/addUser.do")
    @ResponseBody
    public ModelMap addUser(User user ,HttpServletRequest request)  {
        System.out.println("进入用户注册contro");
        ModelMap modelMap = new ModelMap();
        if (userService.addUser(user)) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }
//
//    //删除会员信息
//    @RequestMapping("/deleteUser.do")
//    @ResponseBody
//    public ModelMap deleteUser(@RequestParam(value = "id") String id) {
//        ModelMap modelMap = new ModelMap();
//        if (userService.deleteUser(Long.parseLong(id))) {
//            modelMap.addAttribute("flag", true);
//        } else {
//            modelMap.addAttribute("flag", false);
//        }
//        return modelMap;
//    }



    //用户登录
    @RequestMapping("/login.do")
    @ResponseBody
    public ModelMap login(HttpServletRequest request, User user) throws Exception {
        System.out.println("进入用户登录contro");
        ModelMap modelMap = new ModelMap();
//        String mobile = user.getMobile();
//        String password = Md5.EncoderByMd5(user.getPassword());
//        user.setPassword(password);

        HttpSession session = request.getSession();
         User user1 = userService.login(user);
        if ( user1==null) {
            modelMap.addAttribute("flag",false);
            return modelMap;
        }
        session.setAttribute("loginUser", user1); //把用户信息放session里头
        session.setMaxInactiveInterval(1000);

        modelMap.addAttribute("flag",true);
        return modelMap;
    }


    @ResponseBody
    @RequestMapping(value = "/api/showUser", method = RequestMethod.GET)
    public Pages<User> apiGetShowUser(HttpServletRequest request) throws Exception {
        return showUser(request, "GET");
    }

    @ResponseBody
    @RequestMapping(value = "/api/showUser", method = RequestMethod.POST)
    public Pages<User> apiPostShowUser(HttpServletRequest request) throws Exception {
        return showUser(request, "POST");
    }

    public Pages<User> showUser(HttpServletRequest request, String method) throws Exception {
        String limit = request.getParameter("limit");
        String nowPage = request.getParameter("nowPage");
        String mobile = request.getParameter("mobile");
        System.out.println(mobile);
        // 当前页数
        int nowPaged = Integer.parseInt(null == nowPage ? "1" : nowPage);
        // 每页显示页数
        int limitd = Integer.parseInt(null == limit ? "10" : limit);

        Pages<User> pages = new Pages<>();
        //开始分页,参数1为请求第几页,参数2为请求条数
        PageHelper.startPage(nowPaged, limitd);

        //查询结果
        List<User> userList = userService.getAllUser();
        if (mobile!=""){
            User  user=userService.getUserByMobile(mobile);
            userList= new ArrayList<User>();;
            userList.add(user);
        }
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        int total = (int) pageInfo.getTotal();

        pages.setSuccess(true);
        pages.setMsg("共查询出" + total + "条数据!");
        pages.setRecords(userList);
        pages.setTotal(total);
        pages.setStatus(0);

        return pages;
    }

    @ResponseBody
    @RequestMapping(value = "addAndUpdateUser/{type}", method = RequestMethod.POST)
    public BaseResult addAndUpdateUser(User user, @PathVariable String type, HttpServletRequest request) throws Exception {
        boolean success = true,flag=false;
        String msg = "添加成功!";
        if ("add".equals(type)) {
            flag = userService.addUser(user);
        } else if ("update".equals(type)) {
            System.out.println(user);
            flag = userService.update(user);
        }

        BaseResult result = new BaseResult();
        if ("add".equals(type) && flag==false) {
            success = false;
            msg = "添加失败!";
        } else if ("update".equals(type) && flag==false) {
            success = false;
            msg = "更新失败!";
        } else if ("update".equals(type) && flag==true) {
            success = true;
            msg = "更新成功!";
        }

        result.setSuccess(success);
        result.setMsg(msg);

        return result;
    }

}