package com.example.huaxiaoyu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * @Author: 你与黎明
 * @Description: websocket
 * @create: 2022-11-15 10:45
 * @Version: 1.0
 */
@Configuration
public class WebSocketConfig {
    int maxsize =64*1024;

    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // 在此处设置bufferSize
        container.setMaxTextMessageBufferSize(maxsize);
        container.setMaxBinaryMessageBufferSize(maxsize);
//        container.setMaxSessionIdleTimeout(15 * 60000L);
        return container;
    }

}

