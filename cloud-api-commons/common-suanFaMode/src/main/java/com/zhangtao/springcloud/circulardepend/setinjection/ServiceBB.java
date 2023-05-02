package com.zhangtao.springcloud.circulardepend.setinjection;

import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription: setter方法可以解决循环依赖。 我们AB循环依赖问题只要A的注
 *          入方式是setter且singleton就不会有循环依赖问题。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 19:51
 */
@Component
public class ServiceBB {

    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA){
        this.serviceAA = serviceAA;
        System.out.println("B里面设置了A");
    }
}