package com.zhangtao.springcloud.controll;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhangtao.springcloud.service.PaymentFeignHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月21日 16:09
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")  //全局降级方法
public class OrderFeignHystrixController {

    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
       return paymentFeignHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfoTimeOutFallbackMethod",commandProperties = { //单独指定服务降级方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value="1500")
    })*/
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10/0;
        return paymentFeignHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfoTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒后再试！";
    }

    //全局降级方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试！";
    }
}