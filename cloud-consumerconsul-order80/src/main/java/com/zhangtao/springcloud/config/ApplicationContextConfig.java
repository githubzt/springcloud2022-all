package com.zhangtao.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 1. @ClassDescription: rest 访问
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月16日 16:03
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }
}