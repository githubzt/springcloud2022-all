package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月05日 14:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AliPayment9001 {

    public static void main(String[] args) {
        SpringApplication.run(AliPayment9001.class,args);
    }
}