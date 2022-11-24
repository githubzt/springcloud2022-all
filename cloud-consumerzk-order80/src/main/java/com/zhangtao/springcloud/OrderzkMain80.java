package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月15日 17:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderzkMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderzkMain80.class,args);
    }
}