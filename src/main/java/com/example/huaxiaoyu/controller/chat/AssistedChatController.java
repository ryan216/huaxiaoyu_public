package com.example.huaxiaoyu.controller.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.domain.chat.Assistedchat;
import com.example.huaxiaoyu.mapper.chat.AssistedChatMapper;
import com.example.huaxiaoyu.service.UserService;
import com.example.huaxiaoyu.utils.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 辅助聊天内容分配逻辑
 * @Author: Ryan
 * @Date: 2022/12/20 15:51
 * @Version: 1.0
 */



@RestController
@RequestMapping("/chat")
public class AssistedChatController {
    @Autowired
    private AssistedChatMapper assistedChatMapper;

    @Autowired
    private UserService userService;

    @GetMapping("getRecommendedTopics")
    public R get(@Param("receiveId") Integer receiveId,
                 @Param("sendId") Integer sendId,
                 @Param("num") Integer num
                 )
    {
        User user1 = userService.getById(receiveId);
        if(user1==null){
            return new R("用户id不存在",false);
        }
        String interestCodeList1 = user1.getInterestCodeList();
        int s1=0,s2=0,e1=0;

        User user2 =userService.getById(sendId);
        String interestCodeList2 = user2.getInterestCodeList();
        if(interestCodeList1.startsWith("00") && interestCodeList2.startsWith("00")){
            s1=1;
        }
        if(interestCodeList1.startsWith("01") && interestCodeList2.startsWith("01")){
            s2=1;
        }
        if(interestCodeList1.startsWith("10") && interestCodeList2.startsWith("10")){
            e1=1;
        }
       if(s1==0 &&s2==0 &&e1==0){
           if(interestCodeList1.startsWith("00") || interestCodeList2.startsWith("00")){
               s1=1;
           }
           if(interestCodeList1.startsWith("01") || interestCodeList2.startsWith("01")){
               s2=1;
           }
           if(interestCodeList1.startsWith("10") || interestCodeList2.startsWith("10")){
               e1=1;
           }
       }

        List<Assistedchat> res =new ArrayList<>();
        List<Assistedchat> res1 = new ArrayList<>();
        List<Assistedchat> res2=new ArrayList<>();
        List<Assistedchat> res3=new ArrayList<>();
        if(s1==1){
            LambdaQueryWrapper<Assistedchat> wrapper= new LambdaQueryWrapper<>();
            wrapper.eq(Assistedchat::getType,"sport");
            wrapper.orderByDesc(Assistedchat::getId);
            res1 = assistedChatMapper.selectList(wrapper);
        }
        if(s2==1){
            LambdaQueryWrapper<Assistedchat> wrapper= new LambdaQueryWrapper<>();
            wrapper.eq(Assistedchat::getType,"study");
            wrapper.orderByDesc(Assistedchat::getId);
             res2 = assistedChatMapper.selectList(wrapper);
        }
        e1=1;
        if(e1==1){
            LambdaQueryWrapper<Assistedchat> wrapper= new LambdaQueryWrapper<>();
            wrapper.eq(Assistedchat::getType,"entertainment");
            wrapper.orderByDesc(Assistedchat::getId);
            res3 = assistedChatMapper.selectList(wrapper);
        }

        for(Assistedchat s:res1){
                s.setType("运动");
                res.add(s);
        }
        for(Assistedchat s:res2){
            s.setType("学习");
            res.add(s);
        }
        for(Assistedchat s:res3){
            s.setType("娱乐");
            res.add(s);
        }
        List<Assistedchat> res_new =new ArrayList<>();
        int k=0;
        for(Assistedchat s :res){
            if(k>=num){
                break;
            }
            res_new.add(s);
            k++;
        }

        HashMap<String,List<Assistedchat>> res_final =new HashMap<>();
        res_final.put("topicList",res_new);

        return new R("辅助聊天内容获取成功",res_final,true);
    }

//    @GetMapping("/getAssistedChatTools/{code}")
//    public R getByCode(@PathVariable("code") String code){
//        LambdaQueryWrapper<Assistedchat> wrapper=new LambdaQueryWrapper<>();
//        wrapper.eq(Assistedchat::getCode,code);
//        List<Assistedchat> res = assistedChatMapper.selectList(wrapper);
//        return new R("辅助聊天内容获取成功",res,true);
//    }

}
