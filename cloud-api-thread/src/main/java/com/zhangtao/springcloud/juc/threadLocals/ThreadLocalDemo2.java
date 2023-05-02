package com.zhangtao.springcloud.juc.threadLocals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**资源类**/
class MyData{
    ThreadLocal<Integer> threadLocalInt = ThreadLocal.withInitial(()->0);
    public void add(){
        threadLocalInt.set(1+threadLocalInt.get());
    }
}

/**
 * 1. @ClassDescription:   ThreadLocal不自己remove 线程池内线程复用情况
 *   必须回收自定义的ThreadLocal变量，尤其在线程池场景下，线程经常被复用，如果不清理自定义的ThreadLocal变量，可能会影响后续业务逻辑
 *   造成内存泄露等问题，尽量在代理中使用try-finally块进行回收。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月10日 15:57
 */
public class ThreadLocalDemo2 {

    public static void main(String[] args) {
        // ThreadLocal不自己remove 线程池内线程复用情况模拟
        re_ThreadLocal();
        // ThreadLocal 自己remove 线程池内线程不复用
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(()->{
                    try {
                        Integer beforInt = myData.threadLocalInt.get();
                        myData.add();
                        Integer afterInt = myData.threadLocalInt.get();
                        System.out.println(Thread.currentThread().getName() + "\t beforeInt: " + beforInt + "\t afterInt: " + afterInt);
                    }finally {
                        myData.threadLocalInt.remove();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }

    public static void re_ThreadLocal() {
        // ThreadLocal不自己remove 线程池内线程复用情况模拟
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(()->{
                    Integer beforInt = myData.threadLocalInt.get();
                    myData.add();
                    Integer afterInt = myData.threadLocalInt.get();
                    System.out.println(Thread.currentThread().getName() + "\t beforeInt: " + beforInt + "\t afterInt: " + afterInt);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}