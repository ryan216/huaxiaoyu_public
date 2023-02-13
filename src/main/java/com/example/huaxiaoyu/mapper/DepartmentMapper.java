package com.example.huaxiaoyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.huaxiaoyu.domain.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 你与黎明
 * @Description: 院系
 * @create: 2022-12-13 17:15
 * @Version: 1.0
 */

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
}
