package com.example.huaxiaoyu.service.Impl.friends;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.domain.friends.Friends;
import com.example.huaxiaoyu.mapper.UserMapper;
import com.example.huaxiaoyu.mapper.friends.FriendsMapper;
import com.example.huaxiaoyu.service.friends.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 朋友
 * @create: 2022-12-19 14:50
 * @Version: 1.0
 */
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends> implements FriendsService
{

    @Autowired
    private  FriendsMapper friendsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> get(Integer userId) {
        List<User> res=new ArrayList<>();
        LambdaQueryWrapper<Friends> wrapper1=new LambdaQueryWrapper<>();
        wrapper1.eq(Friends::getSendId,userId);
        List<Friends> friends_1 = friendsMapper.selectList(wrapper1);
        for(Friends f:friends_1){
            if(f.getStatus()==1){
                User user = userMapper.selectById(f.getReceiveId());
                user.setPassword(null);
                res.add(user);
            }
        }
        LambdaQueryWrapper<Friends> wrapper2=new LambdaQueryWrapper<>();
        wrapper2.eq(Friends::getReceiveId,userId);
        List<Friends> friends_2 = friendsMapper.selectList(wrapper2);
        for(Friends f:friends_2){
            if(f.getStatus()==1){
                User user = userMapper.selectById(f.getSendId());
                user.setPassword(null);
                res.add(user);
            }
        }
        return res;
    }
}
