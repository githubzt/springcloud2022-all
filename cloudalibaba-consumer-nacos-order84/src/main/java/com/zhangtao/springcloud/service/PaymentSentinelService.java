package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import com.zhangtao.springcloud.service.impl.PaymentSentinelServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月19日 14:45
 */
@Component
@FeignClient(value = "nacos-payment-provider",fallback = PaymentSentinelServiceImpl.class)
public interface PaymentSentinelService {

    @GetMapping(value = "/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id")Long id);


}
