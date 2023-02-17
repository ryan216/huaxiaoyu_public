package com.example.huaxiaoyu.service.Impl.friends;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huaxiaoyu.domain.LoginUser;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.domain.friends.Friends;
import com.example.huaxiaoyu.mapper.UserMapper;
import com.example.huaxiaoyu.mapper.friends.FriendsMapper;
import com.example.huaxiaoyu.service.friends.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public List<User> get() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
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

    @Override
    public Boolean isFriend(Integer AId, Integer BId) {
        //朋友判断
        boolean isfriend=false;
        LambdaQueryWrapper<Friends> wrapper1 =new LambdaQueryWrapper<>();
        wrapper1.eq(Friends::getSendId,AId);
        wrapper1.eq(Friends::getReceiveId,BId);
        wrapper1.eq(Friends::getStatus,1);
        List<Friends> isfriend1 = friendsMapper.selectList(wrapper1);

        LambdaQueryWrapper<Friends> wrapper2 =new LambdaQueryWrapper<>();
        wrapper2.eq(Friends::getSendId,BId);
        wrapper2.eq(Friends::getReceiveId,AId);
        wrapper2.eq(Friends::getStatus,1);
        List<Friends> isfriend2 =  friendsMapper.selectList(wrapper2);

        if(!isfriend1.isEmpty()||!isfriend2.isEmpty()){
            isfriend=true;
        }
        return isfriend;
    }

    @Override
    public Boolean delete(Integer BId) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer AId = loginUser.getUser().getId();

        LambdaQueryWrapper<Friends> wrapper1 =new LambdaQueryWrapper<>();
        wrapper1.eq(Friends::getSendId,AId);
        wrapper1.eq(Friends::getReceiveId,BId);
        wrapper1.eq(Friends::getStatus,1);
        List<Friends> isfriend1 = friendsMapper.selectList(wrapper1);
        for(Friends friends: isfriend1){
            friendsMapper.deleteById(friends.getId());
        }

        LambdaQueryWrapper<Friends> wrapper2 =new LambdaQueryWrapper<>();
        wrapper2.eq(Friends::getSendId,BId);
        wrapper2.eq(Friends::getReceiveId,AId);
        wrapper2.eq(Friends::getStatus,1);
        List<Friends> isfriend2 =  friendsMapper.selectList(wrapper2);
        for(Friends friends: isfriend2){
            friendsMapper.deleteById(friends.getId());
        }
        return true;
    }

    @Override
    public Boolean add(Integer BId) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer AId = loginUser.getUser().getId();

        Friends  f =new Friends();
        f.setSendId(AId);
        f.setReceiveId(BId);
        f.setStatus(1);
        f.setCreateTime(new Date());
        int flag = friendsMapper.insert(f);
        if(flag==1){
            return true;
        }
        return false;
    }
}
