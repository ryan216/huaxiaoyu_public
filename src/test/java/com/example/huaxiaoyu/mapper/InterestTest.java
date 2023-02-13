//package com.example.huaxiaoyu.mapper;
//
//import com.example.huaxiaoyu.domain.Interest;
//import com.example.huaxiaoyu.service.InterestService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
///**
// * @Author: 你与黎明
// * @Description: 兴趣
// * @create: 2022-12-17 15:08
// * @Version: 1.0
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class InterestTest {
//
//    @Autowired
//    private InterestService service;
//
//    @Test
//    public void add() throws IOException {
//        FileReader fileReader = new FileReader("D:\\java_projects\\huaxiaoyucloud\\huaxiaoyu\\src\\test\\java\\com\\example\\huaxiaoyu\\mapper\\interest.txt");
//        System.out.println(fileReader);
//        BufferedReader b = new BufferedReader(fileReader);
//        String s;
//        int i=1;
//        while((s=b.readLine())!=null){
//            System.out.println(s);
//            Interest d=new Interest();
//            d.setCode(i++);
//            d.setName(s);
//            if(i<=8){
//                d.setType("sport");
//            } else if ( i>8&&i<=10 ) {
//                d.setType("study");
//            }
//            else {
//                d.setType("entertainment");
//            }
//            System.out.println(service.save(d));
//        }
//    }
//}