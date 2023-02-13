//package com.example.huaxiaoyu.mapper;
//
//import com.example.huaxiaoyu.domain.Department;
//import com.example.huaxiaoyu.service.DepartmentService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//
///**
// * @Author: 你与黎明
// * @Description: 院系
// * @create: 2022-12-13 17:18
// * @Version: 1.0
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class department {
//
//    @Autowired
//    private DepartmentService service;
//
//    @Test
//    public void add() throws IOException {
//        FileReader fileReader = new FileReader("D:\\java_projects\\huaxiaoyucloud\\huaxiaoyu\\src\\test\\java\\com\\example\\huaxiaoyu\\mapper\\华科院系.txt");
//        System.out.println(fileReader);
//        BufferedReader b = new BufferedReader(fileReader);
//        String s;
//        int i=1;
//        while((s=b.readLine())!=null){
//            System.out.println(s);
//            Department d1=new Department();
//            d1.setCode(i++);
//            d1.setName(s);
//            System.out.println(service.save(d1));
//        }
//    }
//}
