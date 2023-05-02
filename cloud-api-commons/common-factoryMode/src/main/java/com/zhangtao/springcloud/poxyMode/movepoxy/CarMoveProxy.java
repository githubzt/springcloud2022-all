package com.zhangtao.springcloud.poxyMode.movepoxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 1. @ClassDescription:  定义汽车类的代理类（也可以直接在测试类中实现，作用主要就是封装代理过程）：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 16:12
 */
public class CarMoveProxy {
    /**利用JDK的动态代理实现代理模式**/
    public void doRun(Car car, InvocationHandler invocationHandler){
        //第一个参数 对象.getClass.getClassLoder 第二参数 对象.getClass.getInterfaces 第三个参数 invocationHandler内部类
        CarInterface car1 = (CarInterface) Proxy.newProxyInstance(car.getClass().getClassLoader(),
                car.getClass().getInterfaces(), invocationHandler);
        car1.run();
    }

}