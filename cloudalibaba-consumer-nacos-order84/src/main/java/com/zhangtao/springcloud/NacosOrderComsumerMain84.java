package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月13日 17:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosOrderComsumerMain84 {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderComsumerMain84.class,args);
    }
}