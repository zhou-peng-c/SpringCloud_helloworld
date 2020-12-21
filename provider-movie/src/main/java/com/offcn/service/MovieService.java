package com.offcn.service;

import com.offcn.dao.MovieDao;
import com.offcn.po.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieDao movieDao;

    @Value("${server.port}")
    private String port;

    public Movie getNewMovie() {
        System.out.println("当前电影服务的端口："+port);
        return movieDao.getNewMovie();
    }

}
