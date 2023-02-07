package com.zhangtao.springcloud.juc.baseJMM;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * 1. @ClassDescription:  happened-before 原则案例
 *  happens-before先行发生原则你有了解过吗？     本质上是一种可见性
 *   在JMM中，如果一个操作执行的结果需要对另一个操作可见性或者代码重排序，那么这两个操作之间必须存在happens-before(线性发生)原则。
 * 例子：x=5; y=x 分别被 A,B线程持有，结果不定。如果存在happens-before原则 线程A happens-before 线程B那么结果唯一 ---》包含可见性和有序性的约束
 * happens-before总原则：1、如果一个操作happens-before另一个操作，那么第一个操作的执行结果对第二个操作可见，而且第一个操作的执行顺序在第二个操作之前。
 *     2、两个操作之间存在happens-before关系，并不意味着一定要按照happens-before原则制定的顺序来执行，重排序之后执行结果
 *     和happens-before关系来执行结果一致，那么重排序并不非法
 * happens-before之8条：
 * 1、次序规则:  一个线程内  按照代码顺序执行，后面可见前面执行结果。
 * 2、锁定规则：一个unLock操作先行发生于后面(这里“后面”指时间上的先后)，对同一个锁的lock操作。 即 先释放锁，后面程序才能获得该锁。
 * 3、volatile变量规则： 对一个volatile变量的写操作先行发生于后面对这个变量的读操作，前面写对后面(时间上的)读可见。
 * 4、传递规则： A 先于 B  B 先于 C  那么 A 先于 C
 * 5、线程启动规则(Thread Start Rule)：  Thread对象的start()方法先行发生于此线程的每一个动作。
 * 6、线程中断规则(Thread Interruption Rule):   先调用interrupt()方法设置过中断标志位，才能检测到中断发送
 * 7、线程终止规则(Thread Termination Rule)： 线程中的所有操作都先行发生于对此线程的终止检测，我们可以通过isAlive()等手段检测线程是否已经终止执行。
 * 8、对象终结规则(Finalizer Rule)：一个对象的初始化完成(构造函数执行结束)先行发生于它的finalize()(jvm回收前的最后一个方法)方法的开始。 即 对象没new 不能finalize()
 *  */
public class baseDemo {

    public static void main(String[] args){
        //方案一  结果： 有0 有 1
        long start = System.currentTimeMillis();
        Dog dog1 = new Dog();
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            dog1.setAge();
        }, "t1");
        t1.start();
        ExecutorService pool1 = Executors.newFixedThreadPool(5);
        for (int i=0;i<=20;i++) {
            CompletableFuture.supplyAsync(()->{
                System.out.println("方案一: "+Thread.currentThread().getName()+"\t 结果" +dog1.getAge());
                return "读取完成";
            },pool1);
        }
        LockSupport.unpark(t1);
        pool1.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("方案一耗时：" + (end - start));

        //方案二 全加synchronized
        long start1 = System.currentTimeMillis();
        Dog1 dog2 = new Dog1();
        Thread t3 = new Thread(() -> {
            dog2.setAge();
            LockSupport.park();
        }, "t3");
        t3.start();
        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        for (int i=0;i<=20;i++) {
            CompletableFuture.supplyAsync(()->{
            System.out.println("方案e二: "+Thread.currentThread().getName()+"\t 结果" +dog2.getge());
                return "读取完成";
            },pool2);
        }
        LockSupport.unpark(t3);
        pool2.shutdown();
        long end1 = System.currentTimeMillis();
        System.out.println("方案二耗时：" + (end1 - start1));

        //方案三  volatila synchronized
        long start2 = System.currentTimeMillis();
        Dog2 dog3 = new Dog2();
        Thread t5 = new Thread(() -> {
            dog3.setAge();
            LockSupport.park();
        }, "t5");
        t5.start();
        //模拟异步多线程读取
        ExecutorService pool3 = Executors.newFixedThreadPool(5);
        for (int i=0;i<=20;i++) {
            CompletableFuture.supplyAsync(()->{
                System.out.println("方案三: "+Thread.currentThread().getName()+"\t 结果" + dog3.getge());
                return "读取完成";
             },pool3);
        }
        LockSupport.unpark(t5);
        pool3.shutdown();
        long end2 = System.currentTimeMillis();
        System.out.println("方案三耗时：" + (end2 - start2));

    }
}

/**假设存在A B线程， 线程A先(时间先后)调用了setValue, 然后线程B调用了同一个对象getValue,
 * 那么线程B 收到的返回值是什么？
 * 结论：  可能是0 也可能是1  线程不安全： 1、不满足次序规则 2、俩个方法都没加锁，3、变量没用volatile修饰，
 * 不满足volatile变量规则， 4、传递规则不满足 */
class Dog{
   private int age = 0;
   public int getAge(){
       return age;
   }
   public int setAge(){
       return ++age;
   }
}
/** 俩个方法都加锁： 可以解决问题  写操作加锁没问题 读操作加锁并发降低 不采用 */
class Dog1{
    private int age = 0;
    public synchronized  int getge(){
        return age;
    }
    public synchronized int setAge(){
        return ++age;
    }
}

/** 写加锁，变量用volatile修饰： 可以解决问题  采用
 * 把age定义为volatile变量，由于setter方法对age的修改不依赖age的原值，满足volatile关键字使用场景
 * 理由：利用volatile保证读取操作的可见性，利用synchronized保证复合操作的原子性结合使用锁和volatile变量来减少同步开销*/
class Dog2{
    private volatile int age = 0;
    public int getge(){
        //利用volatile保证读取操作的可见性
        return age;
    }
    public synchronized int setAge(){
        //利用synchronized保证复合操作的原子性
        return ++age;
    }
}