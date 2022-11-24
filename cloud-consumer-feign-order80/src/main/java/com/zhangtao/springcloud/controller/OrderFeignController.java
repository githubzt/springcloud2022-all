package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import com.zhangtao.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月17日 17:34
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("id: " + id);
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value="/consumer/payment/feign/timeout")
    public String payFeignTimeout(){
        //openfeign-ribbon,客户端一般默认等待1秒
        return paymentFeignService.payFeignTimeout();
    }

}