package com.example.huaxiaoyu.domain.notice;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 消息通知
 * @Author: Ryan
 * @Date: 2023/2/15 15:03
 * @Version: 1.0
 */
@Data
public class Notice {


    @TableId("messageId")
    Integer id;

    Integer type;

    Integer result;

    Integer sendId;

    Integer receiveId;

    Integer status;

    Date createdAt;

    Date changeTime;

}
