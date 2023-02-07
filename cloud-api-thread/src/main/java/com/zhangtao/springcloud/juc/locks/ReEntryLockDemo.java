package com.zhangtao.springcloud.juc.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. @ClassDescription: 可重入锁(又名递归锁)：java中ReentrantLocak和synchronized都是可重入锁，优点 可以一定程度避免死锁
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月01日 15:10
 */
public class ReEntryLockDemo {
    //显示锁
    static ReentrantLock reenLock = new ReentrantLock();

    public static void main(String[] args) {
        //隐式锁synchronized修饰的代码块
        ReEntryLockDemo entryLockDemo = new ReEntryLockDemo();
        entryLockDemo.ReEnBlock();
        //隐式锁synchronized修饰的方法

        //显示锁  线程1
        new Thread(()->{
            reenLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----显示外层调用");
                reenLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ----显示中层调用");
                    reenLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "\t ----显示内层调用");
                    }finally {
                        reenLock.unlock();
                    }
                }finally {
                    reenLock.unlock();
                }
            }finally {
                reenLock.unlock();
                /**模拟异常情况： 1、正常情况，加几次锁 释放几次锁
                 *  2、注释掉一个解锁，由于加锁与解锁次数不一样，导致第二个线程一直无法获取到锁，一直等待
                 **/
            }
        },"bb").start();
        // 线程 2
        new Thread(()->{
            reenLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----显示外层调用");
                reenLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ----显示中层调用");
                    reenLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "\t ----显示内层调用");
                    }finally {
                        reenLock.unlock();
                    }
                }finally {
                    reenLock.unlock();
                }
            }finally {
                reenLock.unlock();
            }
        },"cc").start();
    }

    /**synchronized修饰的方法**/
    public synchronized void reEnMethod(){

    }

    /**synchronized修饰的代码块**/
    public void ReEnBlock() {
        final Object obj = new Object();
        new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + "\t ----外层调用");
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName() + "\t ----中层调用");
                    synchronized (obj){
                        System.out.println(Thread.currentThread().getName() + "\t ----内层调用");
                    }
                }
            }
        },"aa").start();
    }
}