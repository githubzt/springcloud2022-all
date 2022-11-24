package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.service.impl.PaymentFeignHystrixServiceImle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月21日 16:17
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-SERVICE",fallback = PaymentFeignHystrixServiceImle.class)
public interface PaymentFeignHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);

}
