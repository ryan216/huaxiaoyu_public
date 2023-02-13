package com.example.huaxiaoyu.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description: 工具类
 * @Author: Ryan
 * @Date: 2023/1/1 19:26
 * @Version: 1.0
 */

@Component
public class Util {

    public  List<Integer> getRandomN(int n, int length){
        Random r = new Random();
        Integer[ ] nums = new Integer[length];
        for(int i=0;i<length;i++){
            nums[i]=i;
        }
        int k=0;
        int j=length-1;
        List<Integer> res =new ArrayList<>();
        while(k<n){
            int i = r.nextInt(j+1);
            int t = nums[i];
            res.add(t);
//            System.out.println(t);
            nums[i]=nums[j];
            nums[j]=t;
            j--;
            k++;
        }
        System.out.println("结果："+res);
        return res;
    }


}
