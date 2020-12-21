package com.offcn.service;

import com.offcn.po.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "PROVIDERMOVIE",fallback = MovieFeignExceptionHandlerService.class)

public interface MovieServiceFeign {

    @GetMapping("/movie")
    public Movie getNewMovie();
}
