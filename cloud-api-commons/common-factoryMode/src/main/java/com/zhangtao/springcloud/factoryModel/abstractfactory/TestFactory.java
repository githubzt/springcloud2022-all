package com.zhangtao.springcloud.factoryModel.abstractfactory;

/**
 * 1. @ClassDescription:   通过抽象工厂我们可以减少代码之间的耦合度，比如后续再新增小鹏汽车类的话，
 *               直接新增小鹏汽车类和小鹏汽车类的工厂类即可，这种实现方式有利于业务的扩展。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:14
 */
public class TestFactory {
    public static void main(String[] args) {
        //生产五菱汽车
        WuLingFactory wuLingFactory = new WuLingFactory();
        CarProduct carProduct = wuLingFactory.productCar();
        carProduct.start();carProduct.run();carProduct.shutdown();

        //生产特斯拉
        TeslaFactory teslaFactory = new TeslaFactory();
        CarProduct carProduct1 = teslaFactory.productCar();
        carProduct1.start();carProduct1.run();carProduct1.shutdown();
    }
}