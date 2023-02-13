package com.example.huaxiaoyu.service.Impl.login;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.huaxiaoyu.domain.LoginUser;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//spring security

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user=userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(user==null){
            throw new RuntimeException("用户名或密码错误");
        }

        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> list=new ArrayList<>();
        list.add("test");
        list.add("admin");
        //封装成UserDetails对象返回
//        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        return new LoginUser(user,list);
    }
}
