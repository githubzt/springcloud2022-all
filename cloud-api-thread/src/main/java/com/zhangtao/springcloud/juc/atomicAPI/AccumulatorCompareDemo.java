package com.zhangtao.springcloud.juc.atomicAPI;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**资源类 统计点赞**/
class ClickNumber{
    int number = 0;
    public synchronized void clickBySynchronized(){
        number++;
    }
    AtomicInteger atomicInteger = new AtomicInteger(0);
    public void clickByAtomicInteger(){
        atomicInteger.getAndIncrement();
    }
    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder(){
         longAdder.increment();
    }
     LongAccumulator longAccumulator = new LongAccumulator((x,y)->{return x+y;},0);
    public void clickByLongAccumulator(){
        longAccumulator.accumulate(1);
    }
}
/**
 * 1. @ClassDescription:  热点商品点赞计数器，点赞数加加统计，不要求实时精确？
 * 需求： 50个线程 每个线程100万次，获取总点赞数
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月09日 11:01
 */
public class AccumulatorCompareDemo {

    public static final int _1W = 10000;
    public static final int threadSize = 50;

    public static void main(String[] args) throws InterruptedException {
        Long startTime;
        Long endTime;
        startTime = System.currentTimeMillis();
        ClickNumber clickNumber = new ClickNumber();
        CountDownLatch downLatch = new CountDownLatch(threadSize);
        for(int i=1;i<=threadSize;i++){
            new Thread(()->{
                try {
                    for (int j = 1; j <= 100 * _1W; j++){
                        clickNumber.clickBySynchronized();
                    }
                }finally {
                   downLatch.countDown();
                }

            },String.valueOf(i)).start();
        }
        downLatch.await();
        endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() +" ----costTime: "+(endTime-startTime)+" 毫秒,clickBySynchronized统计结果：" + clickNumber.number);

        startTime = System.currentTimeMillis();
        CountDownLatch downLatch1 = new CountDownLatch(threadSize);
        for(int i=1;i<=threadSize;i++){
            new Thread(()->{
                try {
                    for (int j = 1; j <= 100 * _1W; j++){
                        clickNumber.clickByAtomicInteger();
                    }
                }finally {
                    downLatch1.countDown();
                }

            },String.valueOf(i)).start();
        }
        downLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() +" ----costTime: "+(endTime-startTime)+" 毫秒,clickByAtomicInteger统计结果：" + clickNumber.atomicInteger);

        startTime = System.currentTimeMillis();
        CountDownLatch downLatch2 = new CountDownLatch(threadSize);
        for(int i=1;i<=threadSize;i++){
            new Thread(()->{
                try {
                    for (int j = 1; j <= 100 * _1W; j++){
                        clickNumber.clickByLongAdder();
                    }
                }finally {
                    downLatch2.countDown();
                }

            },String.valueOf(i)).start();
        }
        downLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() +" ----costTime: "+(endTime-startTime)+" 毫秒,clickByLongAdder统计结果：" + clickNumber.longAdder);

        startTime = System.currentTimeMillis();
        CountDownLatch downLatch3 = new CountDownLatch(threadSize);
        for(int i=1;i<=threadSize;i++){
            new Thread(()->{
                try {
                    for (int j = 1; j <= 100 * _1W; j++){
                        clickNumber.clickByLongAccumulator();
                    }
                }finally {
                    downLatch3.countDown();
                }

            },String.valueOf(i)).start();
        }
        downLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() +" ----costTime: "+(endTime-startTime)+" 毫秒,clickByLongAccumulator统计结果：" + clickNumber.longAccumulator);


    }
}