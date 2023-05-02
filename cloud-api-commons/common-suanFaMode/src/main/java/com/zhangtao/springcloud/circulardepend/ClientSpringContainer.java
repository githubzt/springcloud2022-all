package com.zhangtao.springcloud.circulardepend;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1. @ClassDescription:   scope="prototype"  原型的为什么报错
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 20:13
 */
public class ClientSpringContainer {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);

    }
}