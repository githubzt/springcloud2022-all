package com.zhangtao.springcloud.poxyMode.movepoxy;

/**
 * 1. @ClassDescription:   汽车类实现了汽车接口 重写run方法
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 16:10
 */
public class Car implements CarInterface{
    @Override
    public void run() {
        System.out.println("汽车跑起来了……");
    }
}