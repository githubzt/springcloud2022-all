package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月21日 16:07
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderFeignHystrixMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixMain80.class,args);
    }
}