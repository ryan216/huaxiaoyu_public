package com.example.huaxiaoyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huaxiaoyu.domain.Department;
import com.example.huaxiaoyu.domain.Interest;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 院系
 * @create: 2022-12-13 17:15
 * @Version: 1.0
 */
public interface InterestService extends IService<Interest> {

    HashMap<String, List<Interest>> getInterestDicts();
}
