package com.zhangtao.springcloud.circulardepend.setinjection;

import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription:setter方法可以解决循环依赖。 我们AB循环依赖问题只要A的注
 *            入方式是setter且singleton就不会有循环依赖问题。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 19:41
 */
@Component
public class ServiceAA {

    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB){
        this.serviceBB = serviceBB;
        System.out.println("A里面设置了B");
    }
}