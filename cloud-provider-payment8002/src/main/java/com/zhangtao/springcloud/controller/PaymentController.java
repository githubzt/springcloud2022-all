package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.zhangtao.springcloud.service.PaymentService;

import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月02日 18:10
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private  String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("***********插入结果: "+ result +" (0失败1成功)" );
        if(result>0){
            return  new  CommonResult(200,"插入数据成功,serverPort= "+serverPort,result);
        }else {
            return new CommonResult(400,"插入数据库失败,serverPort= "+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***********查询结果:"+ payment );
        if(payment != null){
            return  new  CommonResult(200,"查询成功,serverPort= "+ serverPort +" ",payment);
        }else {
            return new CommonResult(400,"查询失败，查询id:" + id+" serverPort= "+ serverPort ,null);
        }
    }

    @GetMapping(value ="/payment/lb")  // 自己写一个负载均衡测试效果
    public String getPaymentLb(){
        return serverPort;
    }

    @GetMapping(value="/payment/feign/timeout")
    public String payFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }



}