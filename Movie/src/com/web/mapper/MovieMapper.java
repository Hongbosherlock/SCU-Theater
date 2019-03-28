package com.web.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import com.web.utils.fastJsonUtil;

import java.util.ArrayList;
import java.util.List;

import static com.web.controller.client.doPost;

/**
 * Created by Hongb on 2018/11/29.
 */
public class MovieMapper {

    private fastJsonUtil fastJsonUtil=new fastJsonUtil();

    public    Movie getById(String res, JSONObject jsonObject) throws Exception {
        System.out.println("进入mapper");
        String jsonString = doPost(res,jsonObject);
        Movie movie = fastJsonUtil.parseObject(jsonString,Movie.class);
//jsonstring转class

        return movie;

    }

    public  List<Movie> getAllMovie(String res,JSONObject jsonObject) throws Exception {
        ModelMap modelMap = new ModelMap();
        List<Movie> allMovie = null;
        String jsonString = doPost(res,jsonObject);
        allMovie = fastJsonUtil.parseArray(jsonString,Movie.class);

        return allMovie;
//jsonstring转对象list
    }

//    private List<Movie> getMovieList(String movieJsonString){
//
//        List<Movie> movieList=null;
//
//        JSONArray List = JSON.parseArray(movieJsonString);
//
//        for (Object jsonObject : List) {
//            Movie  movie = JSON.parseObject(jsonObject.toString(), Movie.class);
//            movieList.add(movie);
//        }
//        return movieList;
//    }

    public boolean updateMovie(String res,JSONObject jsonObject) throws Exception {


        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }

    public boolean addMovie(String res,JSONObject jsonObject) throws Exception {

//        Movie movie = new Movie();
        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }

    public boolean deleteMovie(String res,JSONObject jsonObject) throws Exception {

//        Movie movie = new Movie();
        String jsonString = doPost(res,jsonObject);
        String code = fastJsonUtil.getCode(jsonString);
        return code.equals("0");

    }
    public  static void  main(){


    }

}
