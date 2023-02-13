//package com.example.huaxiaoyu.algorithm;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * @Description: 算法优化
// * @Author: Ryan
// * @Date: 2023/1/1 18:25
// * @Version: 1.0
// */
//
//
//public class Test {
//    public static void main(String[] args){
//
////        String[] data = {"apple","orange","pingpong","banana","fruit","basketball"};
//        int length =88;
//        int n=2;
//        List<Integer> res = getRandomN(n, length);
//        for(Integer i :res ){
//            System.out.println(i);
//        }
//
//
//    }
//
//    public static List<Integer> getRandomN(int n, int length){
//        Random r = new Random();
//        Integer[ ] nums = new Integer[length];
//        for(int i=0;i<length;i++){
//            nums[i]=i;
//        }
//        int k=0;
//        int j=length-1;
//        List<Integer> res =new ArrayList<>();
//        while(k<n){
//            int i = r.nextInt(j+1);
//            int t = nums[i];
//            System.out.println(t);
//            nums[i]=nums[j];
//            nums[j]=t;
//            j--;
//            k++;
//        }
//        return res;
//    }
//
//
//}
