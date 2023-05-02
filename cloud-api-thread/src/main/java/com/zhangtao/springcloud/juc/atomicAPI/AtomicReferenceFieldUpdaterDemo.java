package com.zhangtao.springcloud.juc.atomicAPI;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**资源类**/
class MyVar {
    public volatile Boolean isInt = Boolean.FALSE;

    AtomicReferenceFieldUpdater<MyVar,Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"isInt");

    public void init(MyVar myVar){
        if(referenceFieldUpdater.compareAndSet(myVar,Boolean.FALSE,Boolean.TRUE)){
            System.out.println(Thread.currentThread().getName() + "\t ------start init,need 2 seconds");
            //模拟执行了2秒钟
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t ------over init");
        }else {
            System.out.println(Thread.currentThread().getName() + "\t ------已经有线程在执行了！");
        }
    }
}
/**
 * 1. @ClassDescription: 需求： 多个线程并发调用一个类的初始化方法，如果未被初始化过，将执行初始化工作
 *     要求只能被初始化一次，只有一个线程操作成功。
 * 2. @author: ZhangTao
 * 3. @dat: 2023年02月08日 18:57
 */
public class AtomicReferenceFieldUpdaterDemo {

    private static final int threadSize = 10;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(threadSize);
        MyVar myVar = new MyVar();
        for(int i=1;i<=threadSize;i++) {
            try {
                new Thread(() -> {
                    myVar.init(myVar);
                }, String.valueOf(i)).start();
            }finally {
              downLatch.countDown();
            }
        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 结果: " + myVar.isInt);
    }

}