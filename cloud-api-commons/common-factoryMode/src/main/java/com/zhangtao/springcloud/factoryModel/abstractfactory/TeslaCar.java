package com.zhangtao.springcloud.factoryModel.abstractfactory;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:01
 */
public class TeslaCar implements CarProduct{

    @Override
    public void start() {
        System.out.println("特斯拉启动了");
    }

    @Override
    public void run() {
        System.out.println("特斯拉跑了");
    }

    @Override
    public void shutdown() {
        System.out.println("特斯拉停车了");
    }
}