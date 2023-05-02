package com.zhangtao.springcloud.juc.syncUp;

/**
 * 1. @ClassDescription: 锁消除    JIT编译器会无视它，synchronized(o)不存在了, 这个锁对象并没有被共用扩散到其他线程使用，
 *  极端地说就是根本没有加这个锁对象的底层机器码，消除了锁的使用
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月15日 10:17
 */
public class LockClearUPDemo {

    static  Object object = new Object();

    public void m1(){
        //正常案例
        /*synchronized (object){
            System.out.println("-----hello LockClearUPDemo");
        }*/
        //锁消除问题，JIT编译器会无视它，synchronized(o),每次new出来的，不存在了，非常正常
        Object o = new Object();
        synchronized (o){
            System.out.println("-----hello LockClearUPDemo " + o.hashCode() + "\t " + object.hashCode());
        }

    }

    public static void main(String[] args) {
        LockClearUPDemo clearUPDemo = new LockClearUPDemo();
        for (int i = 0; i <= 10; i++) {
            new Thread(()->{
                clearUPDemo.m1();
            },String.valueOf(i)).start();
        }
    }
}