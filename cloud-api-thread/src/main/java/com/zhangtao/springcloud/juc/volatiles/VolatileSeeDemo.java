package com.zhangtao.springcloud.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:  volatile 可见性案例
 *  volatile变量读写过程：read(读取)->load(加载)->use(使用)-assign(赋值)->store(存储)-write(写入)->lock(锁定)->unlock(解锁)
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月06日 16:56
 */
public class VolatileSeeDemo {

    //static  boolean flag = true;
    static volatile boolean flag = true;

    public static void main(String[] args) {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t--- come in");
            while (flag){

            }
            System.out.println(Thread.currentThread().getName() + "\t--- flag被设置为false,程序停止");
        },"t1").start();
        //暂停几秒
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        // main线程来改状态
        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改完成" + flag);


    }

}