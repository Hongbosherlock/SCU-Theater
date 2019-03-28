package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.web.entity.Movie;
import com.web.model.BaseResult;
import com.web.model.Pages;
import com.web.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * 电影信息处理控制器，控制层

 *
 */
@Controller
@RequestMapping("/movie")
public class MovieController {
//    private  Logger log = LogManager.getLogger(MovieController.class);
    @Autowired
    private MovieService movieService;

    //根据id获取电影
    @RequestMapping("/getMovieById.do")
    @ResponseBody
    public ModelMap getMovieById(@RequestParam(value = "id") String id) throws Exception {

        ModelMap modelMap = new ModelMap();
        Movie movie = null;
        movie = movieService.getMovieById(Long.parseLong(id));
        System.out.println("进入controller");
        if (movie == null) {
            System.out.println("获取movie失败！");
            modelMap.addAttribute("flag", false);
        } else {
            System.out.println("获取movie成功！");
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("movie", movie);

        }
        return modelMap;
    }

    //获取所有电影信息
    @RequestMapping("/getAllMovie.do")
    @ResponseBody
    public ModelMap getAllMovie() throws Exception {
        ModelMap modelMap = new ModelMap();
        ArrayList<Movie> allMovie = null;
        allMovie = (ArrayList<Movie>) movieService.getAllMovie();
        if (allMovie == null) {
            modelMap.addAttribute("flag", false);
        } else {
            System.out.println("获取列表成功！");
            modelMap.addAttribute("flag", true);
            modelMap.addAttribute("allMovie", allMovie);
        }
        return modelMap;
    }


    //添加/更新电影信息
    @RequestMapping("/addMovie.do")
    @ResponseBody
    public ModelMap addMovie(Movie movie) throws Exception {
        ModelMap modelMap = new ModelMap();
//        movie.setName("添加电影");
//        movie.setDuration(123);
        if (movieService.addMovie(movie)) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }

    //修改电影信息
    @RequestMapping("/updateMovie.do")
    @ResponseBody
    public ModelMap updateMovie(Movie movie) throws Exception {
        System.out.println("进入更新contr");
        ModelMap modelMap = new ModelMap();
        if (movieService.updateMovie(movie)){
            System.out.println("更新成功！");
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "addAndUpdateMovie/{type}", method = RequestMethod.POST)
    public BaseResult addAndUpdateMovie(Movie movie, @PathVariable String type, HttpServletRequest request) throws Exception {
        boolean success = true,flag=false;
        String msg = "添加成功!";
        if ("add".equals(type)) {
//            movie.setCreateTime(new Date());
//            movie.setUpdateTime(new Date());
           flag = movieService.addMovie(movie);
        } else if ("update".equals(type)) {
//            movie.setUpdateTime(new Date());
            flag = movieService.updateMovie(movie);
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



    //删除电影信息
    @RequestMapping("/deleteMovie.do")
    @ResponseBody
    public ModelMap deleteMovie(@RequestParam(value = "id") String id) throws Exception {
        ModelMap modelMap = new ModelMap();
        if (movieService.deleteMovie(Long.parseLong(id))) {
            modelMap.addAttribute("flag", true);
        } else {
            modelMap.addAttribute("flag", false);
        }
        return modelMap;
    }



    @ResponseBody
    @RequestMapping(value = "/api/showMovie", method = RequestMethod.GET)
    public Pages<Movie> apiGetShowMovie(HttpServletRequest request) throws Exception {
        return showMovie(request, "GET");
    }

    @ResponseBody
    @RequestMapping(value = "/api/showMovie", method = RequestMethod.POST)
    public Pages<Movie> apiPostShowMovie(HttpServletRequest request) throws Exception {
        return showMovie(request, "POST");
    }

    public Pages<Movie> showMovie(HttpServletRequest request, String method) throws Exception {
//        log.debug(method + "查询所有用户信息");
        System.out.println("查询所有电影信息");
        String limit = request.getParameter("limit");
        String nowPage = request.getParameter("nowPage");
//        String order = request.getParameter("order");
//        String name = request.getParameter("name");
//        String startDate = request.getParameter("startDate");
//        String endDate = request.getParameter("endDate");

        // 当前页数
        int nowPaged = Integer.parseInt(null == nowPage ? "1" : nowPage);
        // 每页显示页数
        int limitd = Integer.parseInt(null == limit ? "10" : limit);

        Pages<Movie> pages = new Pages<>();
        //开始分页,参数1为请求第几页,参数2为请求条数
        PageHelper.startPage(nowPaged, limitd);


//        //查询条件
//        UserExample example = new UserExample();
//        UserExample.Criteria criteria = example.createCriteria();
//        if (StringUtil.isNotEmpty(name)) {
//            criteria.andUserNameLike("%" + name + "%");
//        }
////        if (StringUtil.isNotEmpty(sex) && !"-1".equals(sex)) {
////            criteria.andUserSexEqualTo(Short.valueOf(sex));
////        }
//        if (StringUtil.isNotEmpty(startDate) && StringUtil.isNotEmpty(endDate)) {
//            criteria.andCreateTimeBetween(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
//        }
//        example.setOrderByClause(order);

        //查询结果
        List<Movie> movieList = movieService.getAllMovie();
        //取记录总条数
        PageInfo<Movie> pageInfo = new PageInfo<>(movieList);
        int total = (int) pageInfo.getTotal();

        pages.setSuccess(true);
        pages.setMsg("共查询出" + total + "条数据!");
        pages.setRecords(movieList);
        pages.setTotal(total);
        pages.setStatus(0);

        return pages;
    }
}