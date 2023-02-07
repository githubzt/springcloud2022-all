package com.zhangtao.springcloud.juc.locks;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年01月31日 18:02
 */
public class LockSyncDemo {

    Object obj = new Object();

    public void m1(){
        synchronized (obj) {
            System.out.println("-----hello synchronized code block");
        }
    }

    public synchronized void m2(){
        System.out.println("-----hello synchronized code m2");
    }

    public static synchronized void m3(){
        System.out.println("-----hello synchronized code m3");
    }

    public static void main(String[] args) {

    }
}