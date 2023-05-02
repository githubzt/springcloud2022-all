package com.zhangtao.springcloud.circulardepend.constructorinjection;

import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription:  构造方法循环依赖
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 19:43
 */
@Component
public class ServiceB {

   /* private ServiceA serviceA;

    public ServiceB(ServiceA serviceA){
        this.serviceA = serviceA;
    }
*/
}