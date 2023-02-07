package com.zhangtao.springcloud.juc.volatiles;


import java.util.concurrent.TimeUnit;

/** synchronized 具备原子性**/
class MyNumber{
    int num;
    public synchronized void addPlusPlus(){
       num ++;
    }
}
/**测试 volatile 不具备原子性**/
class MyNumber1{
    volatile int num;
    public void addPlusPlus(){
        num ++;
    }
}

/**
 * 1. @ClassDescription:  volatile变量的复合操作不具有原子性，比如： num ++
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月06日 17:50
 */
public class VolatileNoAtomicDemo {

    public static void main(String[] args) {
        MyNumber number = new MyNumber();
        MyNumber1 number1  = new MyNumber1();
        for(int i=1;i<=10;i++){
            new Thread(()->{
                for(int j=1;j<=1000;j++) {
                   // System.out.println(Thread.currentThread().getName());
                    number.addPlusPlus();
                    number1.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        //暂停几秒
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("synchronized锁 ：" + number.num);
        System.out.println("volatile修饰变量不加锁：" + number1.num);
    }
}