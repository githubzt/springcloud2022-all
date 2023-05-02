package com.zhangtao.springcloud.juc.atomicAPI;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class MyNumber{

    AtomicInteger atomicInteger =  new AtomicInteger();

    public void addPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1. @ClassDescription:  高并发下获取i++  CountDownLatch
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 15:38
 */
public class AtomicIntegerDemo {

    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        //线程计数  直到countDownLatch从指定值减少到0 下面线程才能运行
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for(int i=1;i<=SIZE;i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        //等待上面线程全部计算完  日常写着玩儿可以
        //try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        //正式环境用countdownLatch
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t result: " + myNumber.atomicInteger.get());
    }
}