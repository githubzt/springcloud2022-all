package com.zhangtao.springcloud.juc.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**资源类  模拟一个简单的缓存**/
class MyResource{
    Map<String ,String> map = new HashMap<>();
    //=====ReentrantLock 等价于 =======synchronized,
    Lock lock = new ReentrantLock();
    //====ReentrantReadWriteLock 一体俩面 读写互斥、读读共享
     ReentrantReadWriteLock  readWriteLock = new ReentrantReadWriteLock();

     public  void write(String key,String value){
        //lock.lock();
         readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入");
            map.put(key,value);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t 完成写入");
        }finally {
            readWriteLock.writeLock().unlock();  //lock.unlock();
        }
     }

     public void read(String key){
         //lock.lock();
         readWriteLock.readLock().lock();
         try {
             System.out.println(Thread.currentThread().getName() + "\t 正在读取");
             String result = map.get(key);
             //暂停200毫秒
             //try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
             //暂停2000毫秒，演示读锁没有完成之前，写锁无法获得
             try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
             System.out.println(Thread.currentThread().getName() + "\t 完成读取 " + result);
         }finally {
             readWriteLock.readLock().unlock(); //lock.unlock();
         }
     }
}

/**
 * 1. @ClassDescription:  无锁   ->   独占锁   ->   读写锁   ->    邮戳锁：   进化代码演示
 * 2. @author: ZhangTao   一体俩面 读写互斥，读读共享 读没完成时候，其它线程写锁无法获得
 * 3. @date: 2023年02月16日 19:16
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.read(finalI +"");
            },String.valueOf(i)).start();
        }

        //暂停1000毫秒
        try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI +"", finalI +"");
            },"新写锁线程->"+String.valueOf(i)).start();
        }

    }
}