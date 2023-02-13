//package com.example.huaxiaoyu.mapper;
//
//import com.example.huaxiaoyu.domain.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
///**
// * @Description: 用户
// * @Author: Ryan
// * @Date: 2022/12/27 20:07
// * @Version: 1.0
// */
//
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserTest {
//
//    @Autowired
//    private  UserMapper userMapper;
//
//    public String[] names={
//            "土星","木星","天王星","海王星","地球","金星","水星","火星","黑猫仔仔",
//            "小香猪","米熊","皮卡猫","极速蜗牛","呆萌熊宝","精灵鼠","铃铛羊","棉尾兔","可乐",
//            "卡卡","悠悠","小新","兜兜",
//            "孤寂深海", "离殇荡情", "游于长野", "世事苍凉", "永不言变", "陪你演戏", "翩若惊鸿", "清烟无瘾",
//            "惟于莽莽", "尤物佳人", "南鸢离梦", "木槿昔年", "自心难控", "岁月静好", "夏已微凉", "泪水不甜",
//            "我想你笑", "久居深海", "眉眼深情", "移情别恋", "青涩懵懂", "灯火阑珊", "飞颜尘雪", "旧巷情人",
//            "清欢不散", "黑色蔷薇", "笔墨书香", "清峭日暮", "盈笑凝眸", "淡若清风", "沧笙踏歌", "菁华浮梦",
//            "任由漂泊", "谨言慎行", "陪你入睡", "挽袖清风", "勿念初心", "久日与至", "未曾忘你", "别来无恙",
//            "伴你苦乐", "烈酒烫心"};
//
//    @Test
//    public  void add() {
////        for(int i=1;i<50;i++){
//        int i=70;
//        File file =new File("D:\\java_projects\\huaxiaoyucloud\\huaxiaoyu\\src\\test\\java\\com\\example\\huaxiaoyu\\mapper\\files\\male");
//        File[] files = file.listFiles();
//        for(File f:files){
//            String username="user"+i;
//            String sex;
//                sex="male";
////                sex="female";
//            String name_pre="Huster_";
//            Random r = new Random();
//            Integer random = r.nextInt(63);
//            String nickname = name_pre+names[random];
//            User user =new User();
//            user.setPassword("123456");
//            user.setNickname(nickname);
//            user.setDepartmentCode(String.valueOf(i%51));
//            user.setUsername(username);
//            user.setSex(sex);
//            user.setCreateTime(new Date());
//            user.setInterestCodeList("010001,000000,100001");
//
//            String headPhoto="http://82.157.247.7:9092/user/files/"+f.getName();
//            user.setHeadPhoto(headPhoto);
//            userMapper.insert(user);
//            i++;
//            }
//
////        }
//    }
//
//    @Test
//    public  void get() throws IOException {
//        FileReader file =new FileReader("D:\\java_projects\\huaxiaoyucloud\\huaxiaoyu\\src\\test\\java\\com\\example\\huaxiaoyu\\mapper\\四字网名.txt");
//        BufferedReader b = new BufferedReader(file);
//        String s;
//        int i=1;
//        List<String> res= new ArrayList<>();
//        while((s=b.readLine())!=null){
//            res.add("\""+s+"\"");
//        }
//        System.out.println(res);
//    }
//
////        System.out.println(file.listFiles());
//
//}
