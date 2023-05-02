package com.zhangtao.springcloud.juc.atomicAPI;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 16:51
 */
public class AtomicMarkableReferenceDemo {

    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100,false);

    public static void main(String[] args) {

        new Thread(()->{
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t 默认标识: " + marked);
            //暂停几秒线程 等待B线程和A线程拿到一样的标识
            try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
            boolean b = markableReference.compareAndSet(100, 1000, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t"+ "A线程CASresult: "+b);

        },"A").start();

        new Thread(()->{
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t 默认标识: " + marked);
            //暂停几秒线程
            try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t"+ "B线程CASresult: "+b);
            System.out.println(Thread.currentThread().getName() + "\t"+ markableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t"+ markableReference.getReference());

        },"B").start();

    }
}

/**
 *  CAS --- unsafe -- do while + ABA -- AtomicStampedReference,AtomicMarkableReference
 *  AtomicStampedReference :  version号  +1  改过几次
 *  AtomicMarkableReference : 一次性问题解决  是否改过
 *
 * */