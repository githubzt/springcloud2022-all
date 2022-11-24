package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月21日 13:56
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}