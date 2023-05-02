package com.zhangtao.springcloud.poxyMode.staticpoxy;

/**
 * 1. @ClassDescription:  定义汽车类，实现汽车接口：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 15:57
 */
public class Car implements CarInterface{
    @Override
    public void run() {
        System.out.println("汽车在跑……");
    }
}