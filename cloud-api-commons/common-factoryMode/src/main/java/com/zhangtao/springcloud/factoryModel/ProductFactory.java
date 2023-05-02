package com.zhangtao.springcloud.factoryModel;

/**
 * 1. @ClassDescription:  简单工场  生产交通工具的工厂类 ： 定义的类耦合度太高，后续如果新增了对象，
 *      需要多次修改工厂类，怎么解决这个缺点呢？请看抽象工厂模式。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月27日 10:35
 */
public class ProductFactory {

    public Car getCar(){
        return new Car();
    }

    public Plane getPlane(){
        return new Plane();
    }
}