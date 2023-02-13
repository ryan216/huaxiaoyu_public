package com.example.huaxiaoyu.controller.friends;

import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.service.friends.FriendsService;
import com.example.huaxiaoyu.utils.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 朋友
 * @create: 2022-12-19 14:54
 * @Version: 1.0
 */
@RestController
@RequestMapping("friends")
public class FriendsController {
    @Autowired
    private FriendsService friendsService;
    @GetMapping()
    public R get(@Param("userId") Integer userId ){
        List<User> users = friendsService.get(userId);
        return new R("朋友查询成功",users,true);
    }

//    @PostMapping()
//    public  R add(){
//
//    }
}
