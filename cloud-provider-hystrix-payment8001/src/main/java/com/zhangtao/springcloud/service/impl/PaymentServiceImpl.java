package com.zhangtao.springcloud.service.impl;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhangtao.springcloud.dao.PaymentDao;
import com.zhangtao.springcloud.entities.Payment;
import com.zhangtao.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月21日 14:04
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

    @Override
    public String paymentInfo_OK(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_OK,id: "+id+"\t"+" 成功！";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @Override
    public String paymentInfo_TimeOut(Integer id){
        //暂停几秒钟线程
        int timeOut = 2;
        try{ TimeUnit.SECONDS.sleep(timeOut);}catch (Exception e){ e.printStackTrace();}
        //int age = 10/0;
        return "线程池： " + Thread.currentThread().getName()+"\t"+ "paymentInfo_TimeOut,id: "+id+"\t"+"耗时 "+timeOut+" 秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName()+"\t" + "paymentInfo_TimeOut,id: "+id+"\t"+"繁忙！";
    }

    //服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求失败次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("********************id 不能是负数！");
        }
        String serialno = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号是： "+serialno;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id:[ "+id+" ]不能是负数，请稍后再试！";
    }

}