package com.zhangtao.springcloud.juc.loclSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月02日 18:02
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        //synchronized正常唤醒案例
        //m1_normalNotify();
        //synchronized异常唤醒案例  去掉synchronized包裹报错 java.lang.IllegalMonitorStateException
        //m2_unNormalNotify();
        //synchronized 异常唤醒案例  先notify() 后 wait() 一直等待
        //m3_unNormalnotify();
        //Lock Condition 正常唤醒案例
        //m1_normalCondtionSignal();
        //Lock Condition 异常唤醒案例 去掉锁
        //m2_unNormalCondtionSignal();
        //Lock Condition 异常唤醒案例 先Signal() 后 await() 一直等待
        //m3_unNormalCondtionSignal();

        // LockSupport 正常唤醒案例
        //m1_normalLockSupport();
        //LockSupport 异常唤醒案例 先unpark() 后 park()  可以正常唤醒 类似持证上岗，无拦截
        Thread t1 = new Thread(() -> {
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
        }, "t1");
        t1.start();

        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t----发出通知");
        },"t2").start();

    }

    public static void m1_normalLockSupport() {
        // LockSupport 正常唤醒案例
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
        }, "t1");
        t1.start();
        try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t----发出通知");
        },"t2").start();
    }

    public static void m3_unNormalCondtionSignal() {
        //Lock Condition 正常唤醒案例
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t1").start();


        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }

    public static void m2_unNormalCondtionSignal() {
        //Lock Condition 正常唤醒案例
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
        },"t2").start();
    }

    public static void m1_normalCondtionSignal() {
        //Lock Condition 正常唤醒案例
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t1").start();

        try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }

    public static void m3_unNormalnotify() {
        Object obj = new Object();
        new Thread(()->{
            try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
            }
        },"t1").start();


        new Thread(()->{
            synchronized (obj){
                obj.notify();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
            }
        },"t2").start();
    }

    public static void m2_unNormalNotify() {
        //异常唤醒案例  去掉synchronized包裹 报错 java.lang.IllegalMonitorStateException
        Object obj = new Object();
        new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
        },"t1").start();

        try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
                obj.notify();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
        },"t2").start();
    }

    public static void m1_normalNotify() {
        //正常唤醒案例
        Object obj = new Object();
        new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 ");
            }
        },"t1").start();

        try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            synchronized (obj){
                obj.notify();
                System.out.println(Thread.currentThread().getName() + "\t----发出通知");
            }
        },"t2").start();
    }
}