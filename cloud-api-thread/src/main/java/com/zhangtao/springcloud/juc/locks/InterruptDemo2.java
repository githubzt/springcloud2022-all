package com.zhangtao.springcloud.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription: 线程被打标识后是否会立即停止？  如果线程处于正常活跃状态，那么会将该线程的中断标志设置为true，
 *          被设置的线程正常运行不受影响；如果当前处于被阻塞状态(sleep,wait,join…)，在别的线程调用当前线程对像的interrupt方法，
 *          那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月02日 15:45
 */
public class InterruptDemo2 {

    public static void main(String[] args) {
        //线程被打标识后是否会立即停止?
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("---- " + i);
            }
            System.out.println("t1线程调用interrupt()后的中断标识02:" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        System.out.println("t1线程默认的中断标识：" + t1.isInterrupted());
        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        t1.interrupt();
        System.out.println("t1线程调用interrupt()后的中断标识01:" + t1.isInterrupted());
        // t1早就停了不活跃了  中断不活跃的线程，不会产生任何影响
        try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("t1线程调用interrupt()后的中断标识03:" + t1.isInterrupted());

    }
}