package com.zhangtao.springcloud.dao;

import com.zhangtao.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月15日 16:30
 */
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
