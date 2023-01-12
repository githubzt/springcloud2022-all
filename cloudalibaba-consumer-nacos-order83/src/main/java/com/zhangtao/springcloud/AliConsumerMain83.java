package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. @ClassDescription: nacos客户端
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月05日 16:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AliConsumerMain83 {

    public static void main(String[] args) {
        SpringApplication.run(AliConsumerMain83.class,args);
    }
}