package com.zhangtao.springcloud.service.impl;

import com.zhangtao.springcloud.dao.PaymentDao;
import com.zhangtao.springcloud.service.PaymentService;
import com.zhangtao.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月02日 18:03
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    public PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

}