package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月13日 16:47
 */
@RestController
public class SentinelPaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap= new HashMap<>();
    static{
        hashMap.put(1L,new Payment(1L,"111111"));
        hashMap.put(2L,new Payment(2L,"222222"));
        hashMap.put(3L,new Payment(3L,"333333"));
    }

    @GetMapping(value = "/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id")Long id){

        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from mysql,serverPort: "+serverPort,payment);
        return result;
    }
}