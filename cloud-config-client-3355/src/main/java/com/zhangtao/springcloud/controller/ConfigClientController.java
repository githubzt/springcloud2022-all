package com.zhangtao.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. @ClassDescription: 获取配置中心配置
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月24日 21:38
 */
@RestController
@Slf4j
@RefreshScope  //动态刷新,获取最新配置避免重启 需要运维人员访问一下controller  curl -X POST "http://localhost:3355/actuator/refresh"
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){

        return "serverPort: " +serverPort +"\t\n\n configInfo: "+configInfo;
    }
}