package com.example.huaxiaoyu.service.Impl;

import com.example.huaxiaoyu.consumer.Consumer;
import com.example.huaxiaoyu.service.StartChatService;
import com.example.huaxiaoyu.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 你与黎明
 * @Description: 聊天
 * @create: 2022-12-17 17:06
 * @Version: 1.0
 */
@Service
public class StartChatServiceImpl  implements StartChatService {
    @Autowired
    private RedisCache redisCache;

    @Override
    public String startChat(Integer aid , Integer bid) {
        System.out.println("start chat: " + aid + " " + bid);
        String a = aid.toString();
        String b = bid.toString();
        redisCache.setCacheObject(a,b);
        redisCache.setCacheObject(b,a);
//        Consumer.startChat(aid, bid);
        return "start chat success";
    }
}
