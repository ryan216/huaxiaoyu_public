package com.example.huaxiaoyu.controller.user;

import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.service.UserService;
import com.example.huaxiaoyu.service.login.LoginServcie;
import com.example.huaxiaoyu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 你与黎明
 * @Description: 登陆控制
 * @create: 2022-10-04 16:26
 * @Version: 1.0
 */
@RestController
@RequestMapping("user/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    LoginServcie loginServcie;


    @PostMapping()
    public R login(@RequestBody User user)  {
        //登录
//        return userService.login(user);
        return loginServcie.login(user);
    }


    @RequestMapping("/logout")
    public R logout(){

//        return userService.logout();
        return loginServcie.logout();
    }
}
