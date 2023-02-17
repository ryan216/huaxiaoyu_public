package com.example.huaxiaoyu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huaxiaoyu.domain.LoginUser;
import com.example.huaxiaoyu.domain.Message;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.mapper.MessageMapper;
import com.example.huaxiaoyu.mapper.UserMapper;
import com.example.huaxiaoyu.service.MessageService;
import com.example.huaxiaoyu.service.friends.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 聊天记录
 * @create: 2022-11-17 17:11
 * @Version: 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private  MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FriendsService friendsService;


    @Override
    public List<HashMap<String,Object>>  get_records() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();




        List<User> users =new ArrayList<>();

        List<HashMap<String,Object>> res_final =new ArrayList<>();
        LambdaQueryWrapper<Message> wrapper1 =new LambdaQueryWrapper<>();
        wrapper1.eq(Message::getSendId,userId);
        List<Message> message_send = messageMapper.selectList(wrapper1);

        LambdaQueryWrapper<Message> wrapper2 =new LambdaQueryWrapper<>();
        wrapper2.eq(Message::getReceviceId,userId);
        List<Message> message_receive = messageMapper.selectList(wrapper2);

        for(Message message:message_receive){
            User user = userMapper.selectById(message.getSendId());
            if(!users.contains(user)){
                users.add(user);
                HashMap<String,Object> res = new HashMap<>();
                user.setPassword(null);
                res.put("opponent",user);
                res.put("dateTime",message.getCreateTime().toString());
                res.put("isFriend",friendsService.isFriend(userId,user.getId()));
                res_final.add(res);
            }
        }
        for(Message message:message_send){
            User user = userMapper.selectById(message.getReceviceId());
            if(!users.contains(user)){
                users.add(user);
                HashMap<String,Object> res = new HashMap<>();
                user.setPassword(null);
                res.put("opponent",user);
                res.put("dateTime",message.getCreateTime().toString());
                res.put("isFriend",friendsService.isFriend(userId,user.getId()));
                res_final.add(res);
            }
        }
        return res_final;
    }
}
