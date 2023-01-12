package com.zhantao.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月13日 15:43
 */
@Component
public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(444,"按自定义限流测试,globle handlerException----1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"按自定义限流测试,globle handlerException----2");
    }
}