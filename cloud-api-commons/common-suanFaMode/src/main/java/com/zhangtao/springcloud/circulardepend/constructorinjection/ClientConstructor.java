package com.zhangtao.springcloud.circulardepend.constructorinjection;

/**
 * 1. @ClassDescription:  构造方法 无法解决循环依赖问题
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 19:40
 */
public class ClientConstructor {

    //报错
    public static void main(String[] args) {
       // new ServiceA(new ServiceB());
    }
}