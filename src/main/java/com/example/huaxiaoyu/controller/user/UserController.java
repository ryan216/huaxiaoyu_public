package com.example.huaxiaoyu.controller.user;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.huaxiaoyu.domain.Department;
import com.example.huaxiaoyu.domain.Interest;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.service.DepartmentService;
import com.example.huaxiaoyu.service.InterestService;
import com.example.huaxiaoyu.service.UserService;
import com.example.huaxiaoyu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;


import java.util.*;

/**
 * @Author: 你与黎明
 * @Description: user相关接口
 * @create: 2022-12-12 23:13
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Value("${file.ip}")
    private String ip;
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private InterestService interestService;


    @PutMapping("/updateUserInfo")
    public R changeInfo(@RequestBody User user) {
        return userService.changeInfo(user);
    }


    /**
     * @param uuid:
     * @param response:
     * @return void
     * @author
     * @description 获取用户头像
     * @date 2023/2/6 15:08
     */
    @GetMapping("/files/{uuid}")
    public  void  getFiles(@PathVariable String uuid , HttpServletResponse response){
        OutputStream os;
        String basepath = System.getProperty("user.dir") + "/user/files/";  // 定于文件上传的根路径
        List<String> filenames=FileUtil.listFileNames(basepath);
        String fileName= filenames.stream().filter(name -> name.contains(uuid)).findAny().orElse("");
        try{
            if(StringUtils.isNotEmpty(fileName)){
                // attachment是以附件的形式下载，inline是浏览器打开
                response.setHeader("Content-Disposition", " attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basepath + fileName);
                os= response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            System.out.println("文件下载失败");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/checkNameUnique/{username}")
    public R checkNameUnique(@PathVariable("username") String username){
        Boolean flag = userService.checkNameUnique(username);
        if(flag){
            return new R("用户名唯一",true);

        }
        return new R("用户名不唯一",false);
    }


    @GetMapping("/getDepartmentDict")
    public  R  getDepartmentDict(){
        List<Department> list = departmentService.list();
        return new R("院系",list,true);
    }

    @GetMapping("/getInterestDicts")
    public  R  getInterestDicts(){
        HashMap<String, List<Interest>> res = interestService.getInterestDicts();
        return new R("兴趣",res,true);
    }

    @GetMapping("/getUserInfo")
    public  R getUserInfo(HttpServletRequest request){
        String authorization = request.getHeader("authorization");
        return userService.getUserInfo(authorization);
    }







//备用
//    private HashMap<Integer,String> department=new HashMap<Integer,String>() {{
//        put(1, "机械科学与工程学院");
//        put(2, "光学与电子信息学院");
//        put(3, "材料科学与工程学院");
//        put(4, "能源与动力工程学院");
//        put(5, "中欧清洁与可再生能源学院");
//        put(6, "电气与电子工程学院");
//        put(7, "电子信息与通信学院");
//        put(8, "人工智能与自动化学院");
//        put(9, "计算机科学与技术学院");
//        put(10, "船舶与海洋工程学院");
//        put(11, "土木与水利工程学院");
//        put(12, "建筑与城市规划学院");
//        put(13, "环境科学与工程学院");
//        put(14, "航空航天学院");
//        put(15, "网络空间安全学院");
//        put(16, "软件学院");
//        put(17, "生命科学与技术学院");
//        put(18, "数学与统计学院");
//        put(19, "物理学院");
//        put(20, "化学与化工学院");
//        put(21, "武汉光电国家研究中心");
//        put(22, "武汉国际微电子学院");
//        put(23, "工程科学学院（国际化示范学院）");
//        put(24, "未来技术学院");
//        put(25, "集成电路学院");
//        put(26, "医疗装备科学与工程研究院");
//        put(27, "基础医学院");
//        put(28, "医疗装备科学与工程研究院");
//        put(29, "医疗装备科学与工程研究院");
//        put(30, "医疗装备科学与工程研究院");
//        put(31, "医疗装备科学与工程研究院");
//        put(32, "医疗装备科学与工程研究院");
//        put(33, "医疗装备科学与工程研究院");
//        put(34, "医疗装备科学与工程研究院");
//        put(35, "医疗装备科学与工程研究院");
//        put(36, "医疗装备科学与工程研究院");
//        put(37, "医疗装备科学与工程研究院");
//        put(38, "医疗装备科学与工程研究院");
//        put(39, "医疗装备科学与工程研究院");
//        put(40, "医疗装备科学与工程研究院");
//        put(41, "医疗装备科学与工程研究院");
//        put(42, "医疗装备科学与工程研究院");
//        put(43, "医疗装备科学与工程研究院");
//        put(44, "医疗装备科学与工程研究院");
//        put(45, "医疗装备科学与工程研究院");
//        put(46, "医疗装备科学与工程研究院");
//        put(47, "医疗装备科学与工程研究院");
//        put(48, "医疗装备科学与工程研究院");
//        put(49, "医疗装备科学与工程研究院");
//        put(50, "医疗装备科学与工程研究院");
//        put(51, "医疗装备科学与工程研究院");
//
//    }};


}
