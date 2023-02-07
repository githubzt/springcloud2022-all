package com.zhangtao.springcloud.juc.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月02日 9:32
 */
public class DeadLockDemo {

    static ReentrantLock reLock = new ReentrantLock();

    public static void main(String[] args) {

        final Object objA = new Object();
        final Object objB = new Object();

        new Thread(()->{
            synchronized (objA){
                System.out.println(Thread.currentThread().getName() + "\t自己持有A锁,希望获得B锁。");
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (objB){
                    System.out.println(Thread.currentThread().getName() + "\t成功获得"+objB+"锁。");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (objB){
                System.out.println(Thread.currentThread().getName() + "\t自己持有B锁，希望获得A锁。");
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (objA){
                    System.out.println(Thread.currentThread().getName() + "\t成功获得"+objB+"锁。");
                }
            }
        },"B").start();

    }
}