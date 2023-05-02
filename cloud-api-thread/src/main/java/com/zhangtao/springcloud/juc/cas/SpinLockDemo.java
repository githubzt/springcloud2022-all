package com.zhangtao.springcloud.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1. @ClassDescription: 题目：基于CAS实现一个自旋锁，复习CAS思想  自旋锁好处：循环比较获取没有类似wait的阻塞。
 *  通过CAS操作完成自旋锁，A线程先进来调用myLoak方法自己持有锁5秒钟，B线程随后进来发现当前线程持有锁，
 *  所以只能通过自旋等待，直到A释放锁后B随后抢到。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 10:18
 */
public class SpinLockDemo {

    AtomicReference<Thread>  atomicRef =  new AtomicReference<>();

    public void  lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t -----come in");
        while (!atomicRef.compareAndSet(null, thread)) {
        }
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicRef.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t ----task over,unlock……");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.lock();
            //暂停5秒
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.unLock();
        },"A").start();

        //暂停500毫秒，保障线程A 优先于B先启动
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            spinLockDemo.lock();
            spinLockDemo.unLock();
        },"B").start();
    }
}