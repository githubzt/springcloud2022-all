package com.zhangtao.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月16日 9:31
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "springcloud with consul: " +serverport+"\t" + UUID.randomUUID().toString();
    }

}