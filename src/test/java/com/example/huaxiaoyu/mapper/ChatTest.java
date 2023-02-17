//package com.example.huaxiaoyu.mapper;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.example.huaxiaoyu.domain.User;
//import com.example.huaxiaoyu.domain.chat.Assistedchat;
//import com.example.huaxiaoyu.domain.friends.Friends;
//import com.example.huaxiaoyu.mapper.chat.AssistedChatMapper;
//import com.example.huaxiaoyu.service.Impl.friends.FriendsServiceImpl;
//import com.example.huaxiaoyu.service.friends.FriendsService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//import java.util.*;
//
///**
// * @Description: 聊天
// * @Author: Ryan
// * @Date: 2022/12/29 16:27
// * @Version: 1.0
// */
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ChatTest {
//
//    @Autowired
//    private AssistedChatMapper assistedChatMapper;
//
//    @Autowired
//    private FriendsServiceImpl friendsServiceimpl;
//
//    @Test
//    public  void add() throws IOException {
//        FileReader file =new FileReader("D:\\java_projects\\huaxiaoyucloud\\huaxiaoyu\\src\\test\\java\\com\\example\\huaxiaoyu\\mapper\\辅助聊天.txt");
//        BufferedReader b = new BufferedReader(file);
//        String s;
//        int i=1;
//        while((s=b.readLine())!=null){
//            String[] s1 = s.split("\\s+");
//            List<String> res = Arrays.asList(s1);
//            Assistedchat assistedchat =new Assistedchat();
//            assistedchat.setType(res.get(0));
//            assistedchat.setTitle(res.get(1));
//            assistedchat.setContent(res.get(2));
//            if(res.size()==4){
//                assistedchat.setOptionList(res.get(3));
//            }
//            assistedChatMapper.insert(assistedchat);
//
//            System.out.println(res);
//
////            System.out.println(s);
//        }
//    }
//
//    @Test
//    public void test2(){
//        Boolean isFriend=false;
//
//        LambdaQueryWrapper<Friends> wrapper1 =new LambdaQueryWrapper<>();
//        wrapper1.eq(Friends::getSendId,367);
//        wrapper1.eq(Friends::getReceiveId,369);
//        wrapper1.eq(Friends::getStatus,1);
//        List<Friends> isfriend1 = friendsServiceimpl.list(wrapper1);
//
//        LambdaQueryWrapper<Friends> wrapper2 =new LambdaQueryWrapper<>();
//        wrapper2.eq(Friends::getSendId,369);
//        wrapper2.eq(Friends::getReceiveId,367);
//        wrapper2.eq(Friends::getStatus,1);
//        List<Friends> isfriend2 = friendsServiceimpl.list(wrapper1);
//
//        if(!isfriend1.isEmpty()||!isfriend2.isEmpty()){
//            isFriend=true;
//        }
//        System.out.println(isFriend);
//    }
//}
