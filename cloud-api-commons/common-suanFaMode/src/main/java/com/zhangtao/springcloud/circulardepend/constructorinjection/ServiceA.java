package com.zhangtao.springcloud.circulardepend.constructorinjection;

import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription: 构造方法循环依赖
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 19:43
 */
@Component
public class ServiceA {

    /*private ServiceB serviceB;

    public ServiceA(ServiceB serviceB){
        this.serviceB=serviceB;
    }
*/
}