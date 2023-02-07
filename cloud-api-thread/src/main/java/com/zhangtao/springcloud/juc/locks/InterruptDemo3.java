package com.zhangtao.springcloud.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription: 如果当前处于被阻塞状态(sleep,wait,join…)，在别的线程调用当前线程对像的interrupt方法，
 *                       那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月02日 16:16
 */
public class InterruptDemo3 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
           while (true){
               if(Thread.currentThread().isInterrupted()){
                   System.out.println(Thread.currentThread().getName() + " 中断标识:" +
                           Thread.currentThread().isInterrupted() +" 程序停止");
                   break;
               }
               //模拟休息 一直执行死循环   为什么在异常处理再调用中断可以停掉死循环：因为发生异常后中断状态被清除，在打上后就可以被检测到 然后停止
               try { Thread.sleep(200);
               } catch (InterruptedException e) {
                   //解决方案： 再次调用中断
                   Thread.currentThread().interrupt();
                   e.printStackTrace();
               }
               System.out.println("-----hello InterruptDemo3");
           }
        }, "t1");
        t1.start();

        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            t1.interrupt();
        },"t2").start();

    }

}