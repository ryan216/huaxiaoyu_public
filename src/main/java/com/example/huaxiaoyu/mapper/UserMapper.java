package com.example.huaxiaoyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.huaxiaoyu.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: 你与黎明
 * @Description: usermapper interface
 * @create: 2022-10-03 22:05
 * @Version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * @description:
     *
     * @param username String
     *
     * @author: Ryan
     *
     * @create: 2022/10/4 16:38
     *
     * @return: User
    **/
    @Select("select * from user where username=#{username}")
    User getByName(String username);
}
