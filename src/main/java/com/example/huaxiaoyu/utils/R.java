package com.example.huaxiaoyu.utils;

import lombok.Data;

/**
 * @Author: 你与黎明
 * @Description: 表现层数据一致性处理
 * @create: 2022-10-04 10:37
 * @Version: 1.0
 */

@Data
public class R {

    private String msg;

    private Object data;

    private Boolean flag;

    public R() {
    }

    public R(String msg) {
        this.msg = msg;
    }

    public R(String msg, Boolean flag) {
        this.msg = msg;
        this.flag = flag;
    }

    public R(String msg, Object data, Boolean flag) {
        this.msg = msg;
        this.data = data;
        this.flag = flag;
    }

    public R(Object data, Boolean flag) {
        this.data = data;
        this.flag = flag;
    }

    public static  R success(){
        R r=new R();
        r.setFlag(true);
        r.setMsg("成功");
        return r;
    }
    public static  R error(){
        R r=new R();
        r.setFlag(false);
        r.setMsg("失败");
        return r;
    }

}
