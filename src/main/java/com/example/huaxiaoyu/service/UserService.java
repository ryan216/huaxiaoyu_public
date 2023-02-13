package com.example.huaxiaoyu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huaxiaoyu.domain.Interest;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.utils.R;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: userservice interface
 * @create: 2022-10-03 22:07
 * @Version: 1.0
 */
public interface UserService extends IService<User> {

    public R login(User user);

    public R logout();

    /** 
     * @description:
     * @param username String
     * @return: User
    **/
    User getByName(String username);
    IPage<User> queryByPage(int currentPage, int pageSize,String search);


    public  Boolean checkNameUnique(String username);


    public  R changeInfo(User user);


    public  R getUserInfo(String authorization);



}
