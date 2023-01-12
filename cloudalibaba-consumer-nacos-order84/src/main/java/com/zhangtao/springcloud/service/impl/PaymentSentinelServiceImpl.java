package com.zhangtao.springcloud.service.impl;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.Payment;
import com.zhangtao.springcloud.service.PaymentSentinelService;
import org.springframework.stereotype.Component;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月19日 14:47
 */
@Component
public class PaymentSentinelServiceImpl implements PaymentSentinelService {

    @Override
    public CommonResult<Payment> paymentSql(Long id) {
        return new CommonResult<>(444,"----PaymentSentinelService fallback 失败！",new Payment(id,"errorserialno"));
    }


}