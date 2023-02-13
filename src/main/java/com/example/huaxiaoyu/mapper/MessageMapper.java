package com.example.huaxiaoyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.huaxiaoyu.domain.Message;
import com.example.huaxiaoyu.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 你与黎明
 * @Description: 聊天记录
 * @create: 2022-11-17 17:10
 * @Version: 1.0
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
