//package com.example.huaxiaoyu.service.Impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.example.health_code.domain.LoginUser;
//import com.example.health_code.domain.PO.User;
//import com.example.health_code.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//
////        System.out.println(username);
//        //查询用户信息
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername,username);
//        User user = userMapper.selectOne(queryWrapper);
//        //如果没有查询到用户就抛出异常
//        if(Objects.isNull(user)){
//            throw new RuntimeException("用户名或者密码错误");
//        }
//
////        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
////        List<String> list = menuMapper.selectPermsByUserId(user.getId());
//        //把数据封装成UserDetails返回
////        return new LoginUser(user,list);
//        return new LoginUser(user,null);
//    }
//}
