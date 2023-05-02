package com.zhangtao.springcloud.juc.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 1. @ClassDescription:
 *
 * StampedLock = ReentrantReadWriteLock + 读的过程中也允许获取写锁介入
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月17日 13:45
 */
public class StampedLockDemo {

    static int num = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write(){
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "\t 写线程准备修改。");
        try {
            num = num +13;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "\t 写线程修改完成。");
    }
    //悲观锁  读没有完成时候写锁无法获得
    public void read(){
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t come in readlock code block,4 seconds continue……");
        for (int i = 0; i < 4; i++) {
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "正在读取中……");
        }
        try {
            int result = num;
            System.out.println(Thread.currentThread().getName() + "\t 获得的成员变量值： " + result);
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
        }finally {
            stampedLock.unlockRead(stamp);
        }

    }

    //乐观读
    public void tryOptimisticRead(){
        long stamp =  stampedLock.tryOptimisticRead();
        int result = num;
        //估计间隔4秒，很乐观认为读取中没有其它线程修改num值，具体靠判断
        System.out.println("4秒前stampedLock.validate方法值(true无修改，false有修改) " + stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " 正在读取中…… " + i + " 秒后stampedLock.validate方法" +
                    "值(true无修改，false有修改) "+stampedLock.validate(stamp));
        }

        if(!stampedLock.validate(stamp)){
            System.out.println("有人修改过----有写操作");
            stamp = stampedLock.readLock();
            try {
                System.out.println("从乐观读 升级为 悲观读");
                result = num;
                System.out.println("重新悲观读后result: " + result);
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " finally value:" + result);

    }

    public static void main(String[] args) {

        StampedLockDemo resuorce = new StampedLockDemo();
       /* //传统方式 悲观读
        new Thread(()->{
            resuorce.read();
        },"readThread").start();

        //休眠1秒 进入写锁
        try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            resuorce.write();
        },"writeThread").start();*/

        //乐观读
        new Thread(()->{
            resuorce.tryOptimisticRead();
        },"readThread").start();

        //休眠2秒 读过程可以写介入
        try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            resuorce.write();
        },"writeThread").start();
    }

}