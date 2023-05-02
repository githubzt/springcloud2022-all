package com.zhangtao.springcloud.templetModel;

/**
 * 1. @ClassDescription:  通过模板方法模式，可以把认为是不变部分的算法封装到父类实现，而可变部分的则可以通过继承来继续扩展，
 *          行为由父类控制，子类实现。基本方法是由子类实现的，因此子类可以通过扩展的方式增加相应的功能，符合开闭原则。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 18:06
 */
public class TempleteChild extends TemleteClass{
    @Override
    protected void doA() {
        System.out.println("进入了A方法");
    }

    @Override
    protected void doB() {
        System.out.println("进入了B方法");
    }

    @Override
    public boolean isDoAnything(){
        return true;
    }

    public void doAllthing(){
        super.templeteMethod();
    }
}