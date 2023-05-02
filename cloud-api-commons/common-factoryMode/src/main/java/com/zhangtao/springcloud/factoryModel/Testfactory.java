package com.zhangtao.springcloud.factoryModel;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月27日 10:47
 */
public class Testfactory {

    public static void main(String[] args) {
        //测试简单工场
        ProductFactory productFactory = new ProductFactory();
        Car car = productFactory.getCar();
        car.go();
        Plane plane = productFactory.getPlane();
        plane.go();
    }

}