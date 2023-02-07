package com.zhangtao.springcloud.juc.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 1. @ClassDescription: 一个线程不应该由其它线程来强制中断或停止，而是应该由线程自己自行停止，自己命运自己决定。
 *          所以 Thread.stop/suspend,resume都已经废弃了。
 *    java中没有办法立即停止一条线程，提供了一个协商机制“中断”，没增加任何语法，中断过程完全由程序员自己实现，需要调用interrupt方法，
 *    该方法也仅仅是将线程中断标识设置为true,需要自己写代码检测标识位，实现业务逻辑。 可以在别的线程中调用打其它线程标识，也可以在自己线程中调用打标识。
 *    方法:中断线程 void interrupt(); 判断是否中断 static boolean interrupted() ;boolean isInterrupted()
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月02日 11:04
 */
public class InterruptDemo1 {

    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        //线程中断 方法一： volatile
        m1_volatile();
        //方法二： 原子布尔型
        m2_automicboolean();
        //方法三：通过Thread类自带的中断api实例方法实现  interrupt()  isinterrupted()  interrupted()
        m3_interrupt();


    }

    public static void m3_interrupt() {
        //通过Thread类自带的中断api实例方法实现  interrupt()  isinterrupted()  interrupted()
        Thread t3 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true,程序停止");
                    break;
                }
                System.out.println("---hello interrupt");
            }
        }, "t3");
        t3.start();

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        //t4线程调用t3停止
      /*new Thread(()->{
            t3.interrupt();
        },"t4").start();*/
        //自己调用自己停止
        t3.interrupt();
    }

    public static void m2_automicboolean() {
        //线程中断 方法二： atomicBoolean
        new Thread(()->{
            while (true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true,程序停止");
                    break;
                }
                System.out.println("---hello atomicBoolean");
            }
        },"t1").start();

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            atomicBoolean.set(true);
        },"t2").start();
    }

    public static void m1_volatile() {
        //线程中断 方法一： volatile
        new Thread(()->{
            while (true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true,程序停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        },"t1").start();

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            isStop=true;
        },"t2").start();
    }
}