package com.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.Movie;
import com.web.mapper.MovieMapper;
import com.web.service.MovieService;

import com.web.utils.fastJsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    private MovieMapper moviemapper = new MovieMapper();
    private com.web.utils.fastJsonUtil fastJsonUtil=new fastJsonUtil();
    private ModelMap modelMap = new ModelMap();
    //根据id获取movies信息
    @Override
    public Movie getMovieById(long movieId) throws Exception {

        System.out.println("进入service");
        String res="movie/get";
       JSONObject content;
        Movie movie = new Movie();
        movie.setId(movieId);
        content = JSON.parseObject(JSON.toJSONString(movie));
        movie = moviemapper.getById(res,content);
        return movie;
    }
    // 获取所有movie
    @Override
    public List<Movie> getAllMovie() throws Exception {
        String res="movie/list";
        String content = "{ \"id\": 7}";
        JSONObject jsonObject=JSON.parseObject(content);
        List<Movie> allMovie ;
        allMovie = moviemapper.getAllMovie(res,jsonObject);

        return allMovie;
    }

    //更新movies信息
    @Override
    public boolean updateMovie(Movie movie) throws Exception {
        String res="movie/update";
        JSONObject content;
        content = fastJsonUtil.toMovieObject(movie);
        if(moviemapper.updateMovie(res,content)){

            return  true;
        }
        return  false;
    }
    //添加movies信息
    @Override
    public boolean addMovie(Movie movie) throws Exception {
        String res="movie/create";
        JSONObject content;
        content = fastJsonUtil.toMovieObject(movie);
        if(moviemapper.addMovie(res,content)){
            return  true;
        }
        return  false;
    }
    //删除movies信息
    @Override
    public boolean deleteMovie(long movieId) throws Exception {
        String res="movie/delete";
        JSONObject content;
        Movie movie = new Movie();
        movie.setId(movieId);
        content = JSON.parseObject(JSON.toJSONString(movie));
        if(moviemapper.deleteMovie(res,content)){
            return  true;
        }
        return  false;
    }


}
