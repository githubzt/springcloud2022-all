package com.zhangtao.springcloud.poxyMode.staticpoxy;

/**
 * 1. @ClassDescription:  代理模式  代理模式是常用的java设计模式，他的特征是代理类与委托类有同样的接口，
 *               代理类主要负责为委托类预处理消息、过滤消息、把消息转发给委托类，以及事后处理消息等。
 *  静态代理模式
 *      静态代理就是通过自定义的类，去实现代理过程的一种模式，他只能代理这个类，要想代理其他类需要写新的代理方法。
 * 代码结构如下：
 *     定义汽车接口
 *     定义汽车接口的实现类，实现汽车接口。
 *     定义汽车的代理类，实现汽车接口。
 *     在代理类中注入接口类型的对象，调用run方法，在run方法前后实现新方法的调用
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 15:50
 */
public interface CarInterface {
    /**汽车可以跑**/
    public void run();

}