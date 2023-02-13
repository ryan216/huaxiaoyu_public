package com.example.huaxiaoyu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huaxiaoyu.domain.Interest;
import com.example.huaxiaoyu.mapper.InterestMapper;

import com.example.huaxiaoyu.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: 兴趣
 * @create: 2022-12-13 17:16
 * @Version: 1.0
 */
@Service
public class InterestServiceImpl extends ServiceImpl<InterestMapper, Interest>
        implements InterestService {

    @Autowired
    private InterestMapper interestMapper;

    @Override
    public HashMap<String, List<Interest>> getInterestDicts() {
        HashMap<String,List<Interest>> res=new HashMap<>();
        LambdaQueryWrapper<Interest> wrapper1 =new LambdaQueryWrapper<>();
        wrapper1.eq(Interest::getType,"sport");
        LambdaQueryWrapper<Interest> wrapper2 =new LambdaQueryWrapper<>();
        wrapper2.eq(Interest::getType,"study");
        LambdaQueryWrapper<Interest> wrapper3 =new LambdaQueryWrapper<>();
        wrapper3.eq(Interest::getType,"entertainment");
        List<Interest> sport = interestMapper.selectList(wrapper1);
        List<Interest> study =  interestMapper.selectList(wrapper2);
        List<Interest> entertainment =  interestMapper.selectList(wrapper3);
        res.put("sport",sport);
        res.put("study",study);
        res.put("entertainment",entertainment);
        return res;
    }
}
