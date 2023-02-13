package com.example.huaxiaoyu.domain;

import lombok.Data;

/**
 * @Author: 你与黎明
 * @Description: 兴趣
 * @create: 2022-12-17 14:58
 * @Version: 1.0
 */

@Data
public class Interest {
    private Long id;

    private  String code;

    private  String type;

    private  String name;
}
