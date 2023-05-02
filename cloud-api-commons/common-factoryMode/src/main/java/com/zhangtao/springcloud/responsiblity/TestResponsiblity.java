package com.zhangtao.springcloud.responsiblity;

/**
 * 1. @ClassDescription: 我们可以看到，所谓的责任链模式，说白了就是利用了多态的特性，将对象封装成集合，
 * 在调用方法时循环调用对象的方法，利用循环进行调用，责任链模式的典型应用场景就是spring的filter类，有兴趣的可以看下源码。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 22:42
 */
public class TestResponsiblity {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("你好，-------，我是*****琪琪……。");
        //new http处理对象  msg处理对象
        HttpFilter httpFilter = new HttpFilter();
        MsgFilter msgFilter = new MsgFilter();
        //new 责任链处理对象
        FatherFilterChain filterChain = new FatherFilterChain();
        //将http处理对象和msg处理对象加入责任链
        filterChain.add(httpFilter);filterChain.add(msgFilter);
        //传递消息 执行过滤
        filterChain.doFilter(msg.getMsg());
    }
}