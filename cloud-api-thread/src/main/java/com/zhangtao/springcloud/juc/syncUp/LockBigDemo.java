package com.zhangtao.springcloud.juc.syncUp;

/**
 * 1. @ClassDescription: 锁粗化：  假如方法中首位相接，前后相邻的都是同一个对象，那JIT编译器就会把这几个synchronized块合并到一块
 *      加粗加大范围，一次申请锁使用即可。避免次次的申请和释放，提升了性能
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月15日 10:34
 */
public class LockBigDemo {

    static Object object = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (object){
                System.out.println("1111");
            }
            synchronized (object){
                System.out.println("2222");
            } synchronized (object){
                System.out.println("3333");
            }

            //锁粗化
            synchronized (object){
                System.out.println("1111");
                System.out.println("2222");
                System.out.println("3333");
            }

        },"t1").start();

    }
}