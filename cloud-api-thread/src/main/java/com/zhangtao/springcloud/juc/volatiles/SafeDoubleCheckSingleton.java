package com.zhangtao.springcloud.juc.volatiles;

/**
 * 1. @ClassDescription: 著名案例 单例模式DCL(Double check locking)双端锁的发布
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月07日 14:33
 */
public class SafeDoubleCheckSingleton {
    /**通过volatile声明，实现线程安全的延迟初始化**/
    private volatile static SafeDoubleCheckSingleton singleton;
    /**私有化构造方法**/
    private SafeDoubleCheckSingleton(){}

    /**双重锁设计**/
    public static SafeDoubleCheckSingleton getInstance(){
        if(singleton==null){
            //检查一  多线程并发创建对像时，会通过加锁保证只有一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton.class){
                /**检查二 隐患： 多线程环境下，由于重排序，该对象可能还未完成初始化就被其它线程读取
                 * 解决隐患原理：利用volatile,禁止“初始化对象”和“设置singleton指向内存空间”的重排序  */
                if(singleton==null){
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }
        //对象创建完毕，执行getInstance()将不需要获取锁，直接返回创建对象
        return singleton;
    }

}