package com.zhangtao.springcloud.juc.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1. @ClassDescription: 锁降级： 遵循先获取写锁，然后获取读锁，再释放写锁的次序 写锁能够降级为读锁  遵循该次序目的为保证数据一致性。
 * 如果一个线程占有了写锁，在不释放锁的情况下，它还能占有读锁，即写锁降级为读锁
 * 读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月17日 9:33
 */
public class LockDownGradingDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        //正常情况下 A B 俩个线程
       /* //A
        writeLock.lock();
        System.out.println("----写入");
        writeLock.unlock();
        //B
        readLock.lock();
        System.out.println("------读取");
        readLock.unlock();*/

        //本例假设 only one 同一个线程的写厚读  锁降级，  反过来就不行
        writeLock.lock();
        System.out.println("----写入");
        /**
         * 写完之后马上读
         * **/
        readLock.lock();
        System.out.println("------读取");

        writeLock.unlock();
        readLock.unlock();
    }

}