package com.zhangtao.springcloud.poxyMode.movepoxy;

/**
 * 1. @ClassDescription: 动态代理就是JDK帮我们实现的，其原理是基于类的反射，动态的为我们生成一个代理类，
 *        帮我们执行方法，在执行方法的前后位置我们可以定义一些自定义的方法，
 *下面我们来看代码结构：
 *      定义汽车接口
 *      定义汽车接口的实现类，实现汽车接口。
 *      定义代理类，注入接口类型的对象，调用Proxy的newProxyInstence方法，传入对象.getclass.getclassloader，
 *      传入对象.getClass.getInterfaces，传入内部类InvocationHandler，在内部类中实现调用。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 15:51
 */
public interface CarInterface {
    public void run();
}