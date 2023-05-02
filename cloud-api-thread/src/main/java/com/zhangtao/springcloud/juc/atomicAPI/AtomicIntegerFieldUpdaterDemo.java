package com.zhangtao.springcloud.juc.atomicAPI;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**资源类**/
class BankAcount {
    String bankname = "CCB";
    int money = 0;
    //使用传统重锁实现 锁的是整个对象
    public  synchronized void add(){
        money++;
    }
}
/**用AutomicIntegerFieldUpdater来实现**/
class BankAcount1 {
    String bankname = "CCB";
    //更新的对象属性必须使用public volatile 修饰符。
    public volatile int money = 0;
    //因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法newUpdate()创建一个更新器，并且需要设置想要更新的类和属性。
    AtomicIntegerFieldUpdater<BankAcount1> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAcount1.class,"money");
    //不加synchronized，保证高性能原子性，局部微创
    public void transMoney(BankAcount1 bankAcount1){
        fieldUpdater.getAndIncrement(bankAcount1);
    }
}

/**
 * 1. @ClassDescription: 以一种线程安全的方式操作非线程安全对象内的某些字段
 *  需求： 10个线程，每个线程转账1000  不使用synchronized，尝试使用AutomicIntegerFieldUpdater来实现
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 18:56
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) throws InterruptedException {
        BankAcount bankAcount = new BankAcount();
        BankAcount1 bankAcount1 = new BankAcount1();
        CountDownLatch downLatch = new CountDownLatch(10);
        for(int i =0;i<10;i++){
            new Thread(()->{
                try {
                    for(int j=1;j<=1000;j++){
                        bankAcount.add();
                        bankAcount1.transMoney(bankAcount1);
                    }
                }finally {
                    downLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName() + "\tbankAcount result: " + bankAcount.money);
        System.out.println(Thread.currentThread().getName() + "\tbankAcount1 result: " + bankAcount.money);

    }
}