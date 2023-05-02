package com.zhangtao.springcloud;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 17:09
 */
@SpringBootApplication
@EnableDubbo
public class ConsumerDubboMain10005 {

   /* @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDubboMain10005.class,args);
    }
}