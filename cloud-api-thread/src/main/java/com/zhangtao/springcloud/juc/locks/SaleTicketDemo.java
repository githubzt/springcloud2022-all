package com.zhangtao.springcloud.juc.locks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**资源类 模拟3个售票员买完50帐票**/
class Ticket{
    private int num = 50;
    /** new ReentrantLock() ()中什么都没写表示非公平锁   true 公平锁**/
    ReentrantLock lock = new ReentrantLock(true);

    public void sale(){
        lock.lock();
        try {
            if(num>0){
                System.out.println(Thread.currentThread().getName() + " 卖出第:\t " + (num--) + "\t 还剩下: " + num);
            }
        }finally {
            lock.unlock();
        }

    }
}

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月31日 14:54
 */
public class SaleTicketDemo {

    public static void main(String[] args) { //一切程序入口
        //--乐观锁调用方式-- 保证多个线程使用的是同一个AtomicInteger
        AtomicInteger atom =  new AtomicInteger();
        atom.incrementAndGet();

        /********************* 售票员案例  **********************/
        Ticket ticket = new Ticket();
        new Thread(()->{for (int i=0;i<55;i++){ ticket.sale();}},"a").start();
        new Thread(()->{for (int i=0;i<55;i++){ ticket.sale();}},"b").start();
        new Thread(()->{for (int i=0;i<55;i++){ ticket.sale();}},"c").start();
    }
    //---悲观锁调用---------------------------
    public synchronized void m1(){
        //加锁后业务逻辑
    }
    //保证多个线程使用的是同一个lock对象前提下
    ReentrantLock reentrantLock = new ReentrantLock();
    public void m2(){
        reentrantLock.lock();
        try{}finally {
            reentrantLock.unlock();
        }
    }

}