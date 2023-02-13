package com.example.huaxiaoyu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huaxiaoyu.domain.Message;
import com.example.huaxiaoyu.mapper.MessageMapper;
import com.example.huaxiaoyu.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * @Author: 你与黎明
 * @Description: 聊天记录
 * @create: 2022-11-17 17:11
 * @Version: 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
}
