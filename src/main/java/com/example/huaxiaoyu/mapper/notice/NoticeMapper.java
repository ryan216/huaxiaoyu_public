package com.example.huaxiaoyu.mapper.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.huaxiaoyu.domain.notice.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 消息通知
 * @Author: Ryan
 * @Date: 2023/2/15 15:04
 * @Version: 1.0
 */

@Mapper
public  interface NoticeMapper extends BaseMapper<Notice> {
}
