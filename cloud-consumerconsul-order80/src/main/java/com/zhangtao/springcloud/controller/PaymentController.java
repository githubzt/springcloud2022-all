package com.zhangtao.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月16日 16:07
 */
@RestController
@Slf4j
public class PaymentController {

    private static final String INVOKE_URL = "http://cloud-provide-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String getPaymentInfo(){

        return restTemplate.getForObject(INVOKE_URL +"/payment/consul",String.class);
    }

}