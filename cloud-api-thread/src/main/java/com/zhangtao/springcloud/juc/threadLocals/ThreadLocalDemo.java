package com.zhangtao.springcloud.juc.threadLocals;


import java.util.Random;
import java.util.concurrent.TimeUnit;

/**资源类  销售房子**/
class House{
    //方式一   需求1： 传统方法加锁
    int saleCount = 0;
    public synchronized void saleHouse(){
        ++saleCount;
    }

    //方法二  需求2： 初始化变量    现实不使用该方法，写法太繁琐
    ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };
    //方法二  需求2：java8之后这么写
    ThreadLocal<Integer> saleVolume1 = ThreadLocal.withInitial(()->0);
    public void saleByThreadLocal(){
        saleVolume1.set(1+saleVolume1.get());
    }


}
/**
 * 1. @ClassDescription: 需求1： 5个销售卖房子，高层只关注总数
 *       需求2： 5个销售卖完随机数房子，各自独立销售额度，自己业绩提成 分灶吃饭，丰衣足食
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月10日 14:12
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        House house = new House();
        for(int i=1;i<=5;i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5)+1;
                try {
                    for (int j = 1; j <= size; j++) {
                        house.saleHouse();
                        house.saleByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 号销售总量：" + house.saleVolume1.get());
                } finally {
                    house.saleVolume1.remove();
                }
            }, String.valueOf(i)).start();
        }

        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println(Thread.currentThread().getName() + "\t 销售总量：" + house.saleCount);
    }
}