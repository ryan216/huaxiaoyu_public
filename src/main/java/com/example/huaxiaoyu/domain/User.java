package com.example.huaxiaoyu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: 你与黎明
 * @Description: User entity
 * @create: 2022-11-15
 * @Version: 1.0
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String departmentCode;

    private Integer age;

    private String phoneNum;

    private String sex;

    private Date createTime;

    private Date changeTime;

    private String  headPhoto;

    private  String interestCodeList;

}
