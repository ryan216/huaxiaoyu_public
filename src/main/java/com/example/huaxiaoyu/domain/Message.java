package com.example.huaxiaoyu.domain;

import lombok.Data;

import java.util.Date;


/**
 * @Author: 你与黎明
 * @Description: 聊天消息实体
 * @create: 2022-11-17 16:42
 * @Version: 1.0
 */
@Data
public class Message {
    private Long id;

    String sendName;

    String receviceName;

    String sendText;

    Date createTime;

    Date updateTime;

    Integer chatmState;

    Integer TextType;

}
