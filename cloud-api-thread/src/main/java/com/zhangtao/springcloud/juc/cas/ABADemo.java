package com.zhangtao.springcloud.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 1. @ClassDescription:  多线程  ABA问题解决
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 14:33
 */
public class ABADemo {
    //模拟原子类ABA问题
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    //带戳记流水
    static AtomicStampedReference<Integer>  stampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        //已发生的ABA问题
        abaHappen();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "首次版本号: " + stamp);
            //暂停10毫秒，保证后面的D线程初始化拿到的版本号和我一样
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}

            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),
                    stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t" + "2次流水号：" + stampedReference.getStamp());
            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),
                    stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t" + "3次流水号：" + stampedReference.getStamp());

        },"C").start();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "首次版本号: " + stamp);

            //暂停1秒，保证C线程发生ABA
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

            boolean b = stampedReference.compareAndSet(100, 2022, stamp, stamp + 1);
            System.out.println(b +"\t"+ stampedReference.getReference() + "\t" + stampedReference.getStamp());

        },"D").start();
    }

    public static void abaHappen() {
        //模拟原子类ABA问题  中间101操作已经被忽略
        new Thread(()->{
            atomicInteger.compareAndSet(100,101);
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            atomicInteger.compareAndSet(101,100);
        },"A").start();

        new Thread(()->{
            //确保线程A运行完
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicInteger.compareAndSet(100, 200) + "\t" + atomicInteger.get());
        },"B").start();
    }
}