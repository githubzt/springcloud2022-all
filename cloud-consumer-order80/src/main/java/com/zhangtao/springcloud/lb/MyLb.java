package com.zhangtao.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月17日 15:27
 */
@Component
public class MyLb implements LoadBalancer{

    private  AtomicInteger automicInteger = new AtomicInteger(0); //原子类


    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.automicInteger.get();
            next = current >= 2147483647 ? 0 :current+1;
        }while (!this.automicInteger.compareAndSet(current,next)); //自选锁 current 期望值  next 替换值
        System.out.println("************第几次访问次数next:"+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }

}