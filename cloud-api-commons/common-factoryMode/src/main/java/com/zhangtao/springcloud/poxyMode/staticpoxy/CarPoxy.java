package com.zhangtao.springcloud.poxyMode.staticpoxy;

/**
 * 1. @ClassDescription: 通过静态代理，我们可以简单的实现对某个类、接口的代理，但是静态代理也有一定的局限性，
 * 如果我们需要对某个新类进行代理时，又需要代理类实现新的接口去重写一些方法，这样显然是不太方便的，所以JDK给我们提供了动态代理的方法。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 15:58
 */
public class CarPoxy implements CarInterface{
    /**私有化汽车类**/
    private Car car;
    /**创建构造函数**/
    public CarPoxy(Car car){
        this.car = car;
    }
    /**调用汽车类的run方法 在run之前和之后可以定义新方法**/
    @Override
    public void run() {
        beforeRun();
        car.run();
        afterRun();
    }

    /**汽车运行之前调用的方法**/
    public void beforeRun(){
        System.out.println("汽车打着火了……");
    }

    /**汽车运行之后调用的方法**/
    public void afterRun(){
        System.out.println("汽车熄火了……");
    }
}