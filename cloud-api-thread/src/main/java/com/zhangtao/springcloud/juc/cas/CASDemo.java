package com.zhangtao.springcloud.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月07日 16:22
 */
public class CASDemo {

    public static void main(String[] args) {
        //初始值
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //期望值 更新值
        boolean b = atomicInteger.compareAndSet(5, 2022);
        System.out.println(b + "\t" + atomicInteger.get());
        boolean c = atomicInteger.compareAndSet(5, 2022);
        System.out.println(c + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
    }
}