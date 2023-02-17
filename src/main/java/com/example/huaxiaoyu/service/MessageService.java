package com.example.huaxiaoyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huaxiaoyu.domain.Message;
import com.example.huaxiaoyu.domain.User;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 聊天记录
 * @create: 2022-11-17 17:11
 * @Version: 1.0
 */
public interface MessageService extends IService<Message> {

    public List<HashMap<String,Object>> get_records();
}
