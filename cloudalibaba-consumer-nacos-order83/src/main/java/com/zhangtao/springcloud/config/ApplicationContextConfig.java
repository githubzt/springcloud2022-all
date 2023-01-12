package com.zhangtao.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月05日 17:33
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //集群负载均衡注解
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}