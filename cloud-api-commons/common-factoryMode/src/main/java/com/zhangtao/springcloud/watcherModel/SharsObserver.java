package com.zhangtao.springcloud.watcherModel;

/**
 * 1. @ClassDescription: 观察者模式（Observer），又叫发布-订阅模式（Publish/Subscribe），定义对象间一种一对多的依赖关系（注册），
 *          使得每当一个对象改变状态，则所有依赖于它的对象都会得到通知并自动更新（通知）。说白了就是个注册，通知的过程。
 * 代码结构如下：
 *       定义观察者接口和观察者方法。
 *       定义接口的实现类A和B
 *       定义主题类，将观察者传入一个集合内
 *       调用通知方法，循环观察者，传递参数给观察者做出响应。
 * 下面使用代码模拟股市在变动时， 游资和基金之间的动向，这种场景能形象生动的展示出观察者模式：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 14:32
 * * * * * 定义股票观察者父类**/
public interface SharsObserver {
    /**观察之后做出如何反应**/
    public void response(int i);

}