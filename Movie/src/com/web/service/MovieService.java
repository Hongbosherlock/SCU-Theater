package com.web.service;

import com.web.entity.Movie;

import java.util.List;

public interface MovieService {
    //根据id获取movies信息
    public Movie getMovieById(long movieId) throws Exception;
    // 获取所有movie
    public List<Movie> getAllMovie() throws Exception;

    //更新movies信息
    public boolean updateMovie(Movie movie) throws Exception;

    //添加movies信息
    public boolean addMovie(Movie movie) throws Exception;
    //删除movies信息
    public boolean deleteMovie(long movieId) throws Exception;
}
