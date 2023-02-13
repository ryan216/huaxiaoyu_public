package com.example.huaxiaoyu.domain.friends;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 你与黎明
 * @Description: 朋友
 * @create: 2022-12-19 14:44
 * @Version: 1.0
 */

@Data
public class Friends {
    Integer id;

    Integer sendId;

    Integer receiveId;

    Integer status;

    Date createTime;

    Date changeTime;


}
