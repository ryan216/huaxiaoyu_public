package com.example.huaxiaoyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 你与黎明
 * @Description: room
 * @create: 2022-11-15 11:23
 * @Version: 1.0
 */
@Controller
@RequestMapping("room")
public class RoomController {
    @GetMapping("")
    public String enter(){
        return "room";
    }
}
