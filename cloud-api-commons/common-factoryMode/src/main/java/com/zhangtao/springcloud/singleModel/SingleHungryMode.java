package com.zhangtao.springcloud.singleModel;

/**
 * 1. @ClassDescription:   单例设计模式   饿汉模式  线程安全
 * 如果说现在要想控制一个类中实例化对象的产生个数，那么首先就是要锁定类中的构造方法。因为在实例化对象的时候，首先就是要调用类中的构造方法。
 * 既然需要一个实例化对象，那么就可以在类的内部使用static方式来定义一个公共的对象，每一次通过static方法返回唯一的一个对象，
 * 这样外部不管有多少次调用，那么最终的一个类只能够产生唯一的一个对象。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月23日 10:46
 */
public class SingleHungryMode {
    /**把构造方法变为私有，此时在该类外部，就无法new这个类的实例了  构造方法使用了private声明，那么就表示这个构造方法只能被这个类的
     * 内部所使用。既然如此，那么就可以在这个类的内部实例化对象。**/
    private SingleHungryMode(){}

    /**现在的instance在SingleHungryMode里面只是一个普通类的属性，而所有的普通类属性必须在类产生实例化对象之后才可以使用。
     * 是否存在有一种属性，可以让这个类不受SingleHungryMode类实例化对象的控制呢？？如果使用了static声明instance属性，
     * 那么就可以表示在一个类没有产生实例化对象的时候直接使用该属性。
     *  再来创建一个static的成员，表示Singleton 类唯一的实例
    **/
    private static SingleHungryMode instance = new SingleHungryMode();

    /** 在定义一个类的时候，首先想到的就是对类中的属性进行封装。属性一旦进行封装便只能够使用getter()方法访问该属性。
     * 所以现在就需要提供一个getter()方法，可以以同样不受到SingleHungryMode实例化的控制，所以此时应该继续使用static属性。**/
    public static SingleHungryMode getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        SingleHungryMode instance1 = SingleHungryMode.getInstance();
        SingleHungryMode instance = SingleHungryMode.getInstance();
        //不能用这种方式了
        SingleHungryMode singleHungryMode = new SingleHungryMode();

        System.out.println(instance);
        System.out.println(instance1);

        System.out.println(singleHungryMode);
        System.out.println(instance==instance);
        //
        System.out.println(instance==singleHungryMode);
    }

}