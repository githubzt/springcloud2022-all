package com.zhangtao.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 1. @ClassDescription:  nacos配置中心
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月06日 10:13
 */
@RestController
@RefreshScope  //支持nacos的动态刷新功能
public class NacosConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }

}