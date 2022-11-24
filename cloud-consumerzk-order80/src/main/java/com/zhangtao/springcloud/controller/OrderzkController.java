package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月15日 17:17
 */
@RestController
@Slf4j
public class OrderzkController {

    private static final String INVOK_URL="http://cloud-provide-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zk")
    public String getPaymentInfo(){
        return restTemplate.getForObject(INVOK_URL+"/payment/zk", String.class);
    }
}