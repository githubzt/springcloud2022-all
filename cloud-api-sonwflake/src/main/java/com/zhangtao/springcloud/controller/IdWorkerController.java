package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.service.IdWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. @ClassDescription: 雪花算法获取id
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月11日 10:16
 */
@RestController
@Slf4j
public class IdWorkerController {

    @Autowired
    private IdWorkerService idWorkerService;

    @GetMapping(value = "/snowflake")
    public String index(){
        return idWorkerService.getIdBySnowFlake();
    }

}