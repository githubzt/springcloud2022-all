package com.zhangtao.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import com.zhangtao.springcloud.service.PaymentSentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月13日 17:14
 */
@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;
    @Resource
    private RestTemplate restTemplate;
    @Resource  //Openfeign调用方式
    private PaymentSentinelService paymentSentinelService;

    @GetMapping(value = "/consumer/fallback/{id}")
    //@SentinelResource(value = "fallBack")  //无配置 500页面
    //@SentinelResource(value = "fallBack",fallback = "handlerFallback")  //只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //只负责sentinel控制态配置违规
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore={IllegalArgumentException.class}) //都配置  加 忽略报错
    public CommonResult<Payment> fallBack(@PathVariable("id")Long id){
        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSql/" + id, CommonResult.class, id);
        if(id==4){
            throw new IllegalArgumentException("非法参数异常");
        }else if (result.getData() == null){
            throw new NullPointerException("没有对应的记录，空指针异常");
        }
        return result;
    }
    //本例是fallback
   public CommonResult<Payment> handlerFallback(@PathVariable("id")Long id,Throwable e){
        Payment payment = new Payment(id,null);
        return  new CommonResult<>(444,"兜底异常Fallback,Excepotion内容"+e.getMessage(),payment);
    }
    //本例时blockhandler
    public CommonResult<Payment> blockHandler(@PathVariable("id")Long id, BlockException e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445,"blockhandler-sentinel限流，无次流水:"+e.getMessage(),payment);
    }
    //本例是Openfeign调用样例
    @GetMapping(value = "/consumer/paymentsql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id")Long id){
        return paymentSentinelService.paymentSql(id);
    }


}