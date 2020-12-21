package com.offcn.service;

import com.offcn.po.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieFeignExceptionHandlerService implements MovieServiceFeign {
    @Override
    public Movie getNewMovie() {

        Movie movie = new Movie();
        movie.setId(-100);
        movie.setMovieName("无此电影呀...");
        return movie;
    }
}
