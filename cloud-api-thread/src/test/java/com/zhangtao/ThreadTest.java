package com.zhangtao;

import org.junit.Test;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月12日 15:33
 */
public class ThreadTest {

    @Test
    public void testStartOne(){
        Thread  thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开启一个线程");
            }
        });
        thread.start();

        new Thread(()->{
            System.out.println("开启第二个线程");
        },"t1").start();

        Object o = new Object(); //Monotor(监视器)，也就是平时我说的锁(管程)
        new Thread(()->{
            synchronized (o){  //上  锁(管程)

            }
        },"t2").start();

    }
    @Test    //测试守护线程
    public void testDaemon(){

        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 开始运行！ " +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true){

            }
        },"t1");
        t1.setDaemon(true);
        t1.start();

        System.out.println("当前主线程：" + Thread.currentThread().getName() + "\t ----end");
    }

}