package com.example.crushingsystem;

import com.example.crushingsystem.service.impl.MatchingServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CrushingsystemApplication {

    public static void main(String[] args) {
        MatchingServiceImpl.matchingPool.start();  // 启动匹配线程
        SpringApplication.run(CrushingsystemApplication.class , args);
    }

}
