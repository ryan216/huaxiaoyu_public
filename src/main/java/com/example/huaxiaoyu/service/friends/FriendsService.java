package com.example.huaxiaoyu.service.friends;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.domain.friends.Friends;

import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 朋友
 * @create: 2022-12-19 14:49
 * @Version: 1.0
 */
public interface FriendsService extends IService<Friends> {
    public List<User> get();

    public  Boolean isFriend(Integer AId,Integer BId);

    public Boolean delete(Integer BId);

    public  Boolean add(Integer BId);

}
