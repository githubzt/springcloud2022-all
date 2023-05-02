package com.zhangtao.springcloud.simplecalculate;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1. @ClassDescription: 给定一个整数组nums和一个目标值target,请你在该组中找出和为目标值的那俩个整数，并返回他们的数组下标。
 *          你可以假设每种输入只会对应一个答案，但是，数组中同一个元素不能使用俩次。 nums = [2,7,11,15]  taget=9
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月27日 18:12
 */
public class TwoSum {
    //哈希算法
    public int[] twoSum(int[] nums, int target) {
        int[] arr = {};
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int partner =  target - nums[i];
            if(hashMap.containsKey(partner)){
                if(arr.length==0){
                    //存入位置下标
                    arr = new int[]{hashMap.get(partner),i};
                }else{
                    arr = Arrays.copyOf(arr,arr.length+2);
                    arr[arr.length-2] = hashMap.get(partner);
                    arr[arr.length-1] = i;
                }
            }
            hashMap.put(nums[i],i);
        }
        return arr;
    }
    //暴力破除
    public int[] twoSum1(int[] nums, int target) {
        int[] arr = {};
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j < nums.length;j++){
                if(target - nums[i] == nums[j]){
                    System.out.println("nums[ "+i+" ] + nums["+j+"] = "+target+"");
                    if(arr.length==0){
                        arr = new int[]{i, j};
                    }else {
                        arr = Arrays.copyOf(arr,arr.length+2);
                        arr[arr.length-2] =i;
                        arr[arr.length-1]=j;
                    }
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        TwoSum suanfa = new TwoSum();
        int[] nums = new int[]{2,7,11,3,15,6,12};
        int target = 9;
        int[] ints = suanfa.twoSum(nums,target);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        System.out.println("-----------算法2-------");
        int[] ints1 = suanfa.twoSum1(nums, target);
        for (int i = 0; i < ints1.length; i++) {
            System.out.println(ints1[i]);
        }

    }
}