package com.offcn.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.offcn.dao.UserDao;
import com.offcn.po.Movie;
import com.offcn.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;



    /**
     * 根据ID得到用户对象
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        User user = userDao.getUser(id);
        return user;
    }

    /**
     * 购买最新的电影票
     * @param id 用户ID
     * @return
     */
    @HystrixCommand(fallbackMethod = "buyMovieFallbackMethod")
    public Map<String, Object> buyMovie(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //1.查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);
        //2.查到最新电影票
        Movie movie = restTemplate.getForObject("http://PROVIDERMOVIE//movie", Movie.class);
        result.put("movie", movie);
        return result;
    }

    public Map<String, Object> buyMovieFallbackMethod(Integer id){
        User user = new User();
        user.setId(-1);
        user.setUserName("未知用户");
        Movie movie = new Movie();
        movie.setId(-100);
        movie.setMovieName("无此电影");
        Map<String,Object> result = new HashMap<>();
        result.put("user",user);
        result.put("movice",movie);
        return result;
    }
}
