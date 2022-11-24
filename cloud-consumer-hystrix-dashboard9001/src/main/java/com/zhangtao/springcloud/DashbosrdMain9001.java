package com.zhangtao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月22日 14:01
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DashbosrdMain9001 {
    //访问地址：
    public static void main(String[] args) {
        SpringApplication.run(DashbosrdMain9001.class,args);
    }
}