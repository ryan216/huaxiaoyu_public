package com.example.huaxiaoyu.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huaxiaoyu.domain.Interest;
import com.example.huaxiaoyu.domain.LoginUser;
import com.example.huaxiaoyu.domain.User;
import com.example.huaxiaoyu.domain.friends.Friends;
import com.example.huaxiaoyu.mapper.UserMapper;
import com.example.huaxiaoyu.mapper.friends.FriendsMapper;
import com.example.huaxiaoyu.service.UserService;
import com.example.huaxiaoyu.utils.JwtUtil;
import com.example.huaxiaoyu.utils.R;
import com.example.huaxiaoyu.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: 你与黎明
 * @Description: userserviceimpl
 * @create: 2022-10-03 22:10
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Value("${file.ip}")
    private String ip;

    @Autowired
    private RedisCache redisCache;


//    @Override
//    public R login(User user) {
//        //AuthenticationManager authenticate进行用户认证,该方法会去调用UserDetailsServiceImpl.loadUserByUsername
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        //如果认证没通过，给出对应的提示
//        if( Objects.isNull(authenticate)){
//            throw new RuntimeException("登录失败");
//        }
//        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
//        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
//        String userid = loginUser.getUser().getId().toString();
//        String jwt = JwtUtil.createJWT(userid);
//        Map<String,String> map = new HashMap<>();
//        map.put("token",jwt);
//        //把完整的用户信息存入redis  userid作为key
//        redisCache.setCacheObject("login:"+userid,loginUser);
//        return new R("登录成功",map,true);
//    }
//
//    @Override
//    public R logout() {
//        //获取SecurityContextHolder中的用户id
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        Long userid = loginUser.getUser().getId();
//        //删除redis中的值
//        redisCache.deleteObject("login:"+userid);
//        return new R("注销成功",true);
//    }

    @Override
    public R login(User user) {
        String pd=user.getPassword();
        String username=user.getUsername();
        User user1 = userMapper.getByName(username );
        if(user1==null){
            return new R("用户名不存在，登陆失败",false);
        }
        if(!pd.equals(user1.getPassword())){
            return new R("密码错误，登陆失败",false);
        }
        user1.setPassword(null);
        return new R("登陆成功",user1,true);
    }

    @Override
    public R logout() {
        return null;
    }



    @Override
    public User getByName(String username) {
        return userMapper.getByName(username);
    }
    @Override
    public IPage<User> queryByPage(int currentPage, int pageSize,String search) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        //避免空
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getUsername,search);
        }
        IPage<User> bookIPage = new Page<>(currentPage, pageSize);
        return userMapper.selectPage(bookIPage,wrapper);
    }

    @Override
    public Boolean checkNameUnique(String username) {
        User user = userMapper.getByName(username);
        if(user!=null){
            return false;
        }
        return true;
    }

    @Override
    public R changeInfo(User user) {
        User user0 = userMapper.getByName(user.getUsername());
        if(user0==null){
            return new R("用户名不存在,修改失败",false) ;
        }
        String data_base64 = user.getHeadPhoto();
        if(data_base64 !=null){
            byte[] b = Base64.getDecoder().decode(data_base64);
            //文件唯一标识
            String uuid = IdUtil.fastSimpleUUID();
            String rootFilePath=System.getProperty("user.dir")+"/user/files/"+uuid+"_"+user.getUsername()+".jpg";
            File rootFile = new File(rootFilePath);
            if (!rootFile.getParentFile().exists()) {
                rootFile.getParentFile().mkdirs();
            }
            String url=ip+"/user/files/"+uuid+"_"+user.getUsername()+".jpg";
            FileUtil.writeBytes(b, rootFilePath);  // 把文件写入到上传的路径
            user.setHeadPhoto(url);
        }
        user.setChangeTime(new Date());
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        int flag=userMapper.update(user,wrapper);
        User res = userMapper.getByName(user.getUsername());
        res.setPassword(null);
        if(flag!=1){
            return new R("修改失败",false) ;
        }
        return new R("修改成功",res,true) ;
    }

    @Override
    public R getUserInfo(String authorization) {
        if (!org.springframework.util.StringUtils.hasText(authorization)) {
            //放行
            return new R("token非法",false);
        }
        //解析token

        String userid;
        String token = null;
        try {
            String []tokens=authorization.split(" ");
            if(tokens.length>1){
                token = tokens[1];
            }
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return new R("token非法",false);
        }

        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        User user = loginUser.getUser();

        return new R("用户信息获取成功！",user,true);
    }




}
