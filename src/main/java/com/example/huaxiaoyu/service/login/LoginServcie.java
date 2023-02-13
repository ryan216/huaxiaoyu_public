package com.example.huaxiaoyu.service.login;


import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.utils.R;


public interface LoginServcie {
    R login(User user);

    R logout();

}
