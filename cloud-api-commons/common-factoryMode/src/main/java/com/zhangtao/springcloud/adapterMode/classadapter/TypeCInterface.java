package com.zhangtao.springcloud.adapterMode.classadapter;

/**
 * 1. @ClassDescription:  类适配器模式
 * 利用继承和实现完成适配器的转换，代码结构如下：
 *               定义接口A
 *               定义类B
 *              定义适配器类C 继承B 实现接口A
 *              在适配器类中既可以实现接口A的方法 也可以重写B类的方法
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:14
 */
public interface TypeCInterface {

    public void v5();
}