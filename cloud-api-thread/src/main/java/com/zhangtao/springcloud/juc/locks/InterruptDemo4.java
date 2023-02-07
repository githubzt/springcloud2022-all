package com.zhangtao.springcloud.juc.locks;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月02日 17:17
 */
public class InterruptDemo4 {

    public static void main(String[] args) {
        //测试当前线程是否被中断(检查中断标志)，返回一个boolean并清除中断状态
        //第二次再调用时中断状态已经被清除，将返回一个false

        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("---1");
        Thread.currentThread().interrupt(); //中断标志设置为true
        System.out.println("---2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());

    }
}