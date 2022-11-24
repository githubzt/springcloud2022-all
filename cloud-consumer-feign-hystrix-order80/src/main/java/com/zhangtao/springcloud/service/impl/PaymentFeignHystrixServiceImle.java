package com.zhangtao.springcloud.service.impl;

import com.zhangtao.springcloud.service.PaymentFeignHystrixService;
import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月21日 22:17
 */
@Component
public class PaymentFeignHystrixServiceImle implements PaymentFeignHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFeignHystrixService fallback paymentInfo_OK 失败！";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----PaymentFeignHystrixService fallback paymentInfo_TimeOut 失败！";
    }
}