package com.zhangtao.springcloud.circulardepend.setinjection;

/**
 * 1. @ClassDescription: setter方法可以解决循环依赖。 我们AB循环依赖问题只要A的注
 *            入方式是setter且singleton就不会有循环依赖问题。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 19:53
 */
public class ClientSet {

    public static void main(String[] args) {
        //创建serviceAA  调用的无参构造方法
        ServiceAA aa = new ServiceAA();
        //创建serviceBB
        ServiceBB bb = new ServiceBB();
        //将serviceAA注入到serviceBB中
        bb.setServiceAA(aa);
        //将serviceBB注入到serviceAA中
        aa.setServiceBB(bb);

    }
}