package com.zhangtao.springcloud.singleModel;

/**
 * 1. @ClassDescription:  单例设计模式  懒汉模式 线程不安全
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月23日 10:43
 */
public class SingleLazyMode {
    /**把构造方法变为私有，此时在该类外部，就无法new这个类的实例了**/
    private SingleLazyMode(){}
    /**再来创建一个static的成员，表示Singleton 类唯一的实例**/
    private static SingleLazyMode instance = null;
    //加volitile关键字 避免指令重排
    private static volatile SingleLazyMode instance1 = null;
    /** static 和 类相关，和实例无关，类在内存中只有一份，static 成员也就只有一份**/
    public static SingleLazyMode getInstance(){
        if (null == instance){
            instance = new SingleLazyMode();
        }
        return instance;
    }
    //线程安全模式锁粒度大
    public static synchronized SingleLazyMode getInstance1(){
        if(null == instance){
            instance = new SingleLazyMode();
        }
        return instance;
    }
    //线程安全模式锁粒度小 双端核对
    public static SingleLazyMode getInstance2(){
        if(null == instance) {
            synchronized (SingleLazyMode.class) {
                if (null == instance) {
                    instance = new SingleLazyMode();
                }
            }
        }
        return instance;
    }

    //加volitile版双端核对
    public static SingleLazyMode getInstance3(){
        if(null==instance1){
            synchronized (SingleLazyMode.class){
                if(null==instance1){
                    instance1 = new SingleLazyMode();
                }
            }
        }

        return instance1;
    }

    public static void main(String[] args) {
        SingleLazyMode instance = SingleLazyMode.getInstance();
        SingleLazyMode instance1 = SingleLazyMode.getInstance();

        SingleLazyMode instance3 = SingleLazyMode.getInstance3();
        SingleLazyMode instance31 = SingleLazyMode.getInstance3();

        System.out.println(instance);
        System.out.println(instance1);
        System.out.println(instance == instance1);

        System.out.println(instance3);
        System.out.println(instance31);
        System.out.println(instance31 == instance3);

    }

}