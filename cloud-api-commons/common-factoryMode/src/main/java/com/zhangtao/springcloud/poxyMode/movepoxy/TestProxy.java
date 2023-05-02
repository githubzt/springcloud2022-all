package com.zhangtao.springcloud.poxyMode.movepoxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 1. @ClassDescription:  执行结果和静态代理的结果一致，但是我们可以通过代码了解，唯一的不同就是动态代理无需我们自定义代理类，
 *              二是通过java的反射机制为我们动态的生成了一个代理类去执行我们定义的方法。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 16:26
 */
public class TestProxy {

    public static void main(String[] args) {
        CarMoveProxy carMoveProxy = new CarMoveProxy();
        Car car = new Car();
        carMoveProxy.doRun(car, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("汽车启动……");
                Object invoke = method.invoke(car, args);
                System.out.println("汽车关闭……");
                return invoke;
            }
        });
    }

}