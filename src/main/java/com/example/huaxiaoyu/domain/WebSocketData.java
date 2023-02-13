package com.example.huaxiaoyu.domain;

import lombok.Data;

import javax.websocket.Session;

/**
 * @Author: 你与黎明
 * @Description: websocket data
 * @create: 2022-11-15 10:47
 * @Version: 1.0
 */
@Data
public class WebSocketData {
    /**
     * 当前连接
     */
    private Session session;
    /**
     * 当前通讯ID
     */
    private String communicationId;
}

