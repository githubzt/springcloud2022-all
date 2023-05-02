package com.zhangtao.springcloud.factoryModel.abstractfactory;

/**
 * 1. @ClassDescription: 定义生产五菱汽车的工厂类：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:11
 */
public class WuLingFactory implements CarProductFactory{
    @Override
    public CarProduct productCar() {
        return new WuLingCar();
    }
}