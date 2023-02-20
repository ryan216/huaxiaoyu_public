package com.example.huaxiaoyu.service.notice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huaxiaoyu.domain.notice.Notice;
import com.example.huaxiaoyu.utils.R;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 消息
 * @Author: Ryan
 * @Date: 2023/2/15 15:08
 * @Version: 1.0
 */
public interface NoticeService extends IService<Notice> {

    public HashMap<String,List<HashMap<String,Object>>>  get();


    public  Boolean add(Integer receiveId,Integer type,Integer result);

    public  Notice getByMessageId(Integer messageId);
}
