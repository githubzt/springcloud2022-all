package com.zhangtao.springcloud.facadePattem;

/**
 * 1. @ClassDescription: 定义门面类 可以通过此类实现系统ABC之间的复杂调用
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:30
 */
public class ControlClas {

    //私有化三个系统的类
    private SystemA systemA = new SystemA();
    private SystemB systemB = new SystemB();
    private SystemC systemC = new SystemC();

    //通过此方法实现ABC之间的复杂调用
    public void doSomthing(){
        systemA.doA();
        systemB.doB();
        systemC.doC();
    }

}