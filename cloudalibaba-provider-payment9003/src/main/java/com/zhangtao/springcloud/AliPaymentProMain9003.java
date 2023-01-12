package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月13日 16:43
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AliPaymentProMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(AliPaymentProMain9003.class,args);
    }
}