package com.zhantao.springcloud.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import com.zhantao.springcloud.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月13日 14:23
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping(value = "/byResuorce")
    @SentinelResource(value = "byResuorce",blockHandler = "handleException")
    public CommonResult byResuorce(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2022L,"serialno001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping(value = "/ratelimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按Url限流测试OK",new Payment(2022L,"serialno002"));
    }

    @GetMapping(value = "/ratelimit/customerBlolkHandler")  //自定义兜底方法
    @SentinelResource(value = "customerBlolkHandler",blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlolkHandler(){
        return new CommonResult(200,"按自定义限流测试OK",new Payment(2022L,"serialno003"));
    }


}