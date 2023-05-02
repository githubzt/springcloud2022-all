package com.zhangtao.springcloud.factoryModel.abstractfactory;

/**
 * 1. @ClassDescription: 解耦：分离职责，把复杂对象的创建和使⽤的过程分开。
 * 2. @author: ZhangTao  定义生产汽车的工厂类
 * 3. @date: 2023年03月15日 19:09
 */
public interface CarProductFactory {

    CarProduct productCar();
}
