package com.zhangtao.springcloud.templetModel;

/**
 * 1. @ClassDescription:  模板方法模式
 *         定义一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
 *应用场景：
 *   例如生成PDF模板，有固定的logo位置、固定的表格排版，不固定的就是数据的解析，可以将logo的位置、基础样式放置在父类，将数据的解析放置在子类。
 *代码结构如下：
 *    定义抽象父类，在其中定义属性与方法，把不变的格式封装起来。
 *    定义子类，继承父类，重写某些方法，加入自己的逻辑，调用父类的总方法，完成处理。
 *    定义测试类，通过子类调用父类的方法完成逻辑
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:59
 */
public abstract class TemleteClass {
    /**基本方法**/
    protected abstract void doA();
    protected abstract void doB();

    /**返回布尔值**/
    public  boolean isDoAnything(){
        return true;
    }

    /**模板方法**/
    public void templeteMethod(){
        doA();
        if(isDoAnything()){
            doB();
        }
    }
}