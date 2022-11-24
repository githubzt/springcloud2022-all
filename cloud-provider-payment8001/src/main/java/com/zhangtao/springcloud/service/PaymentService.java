package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月02日 17:59
 */
public interface PaymentService{

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}