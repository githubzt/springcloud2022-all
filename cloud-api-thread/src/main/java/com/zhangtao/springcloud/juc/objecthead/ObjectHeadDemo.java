package com.zhangtao.springcloud.juc.objecthead;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月13日 15:13
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {
        Object object = new Object();//new 一个对象 占用多少内存？ 没有实例数据、对其填充，占用16个字节，mark Word8字节 类型指针8字节
        System.out.println(object.hashCode()); //这个hashcode记录在对象的什么地方？

        synchronized (object){ //到底有没有加锁 怎么知道被锁了多少次  那些地方标注

        }

        System.gc();  //手动收集垃圾  15次可以从新生代->养老去

        Customer customer = new Customer();
        /** 压缩指针说明
         * java -XX:+PrintCommandLineFlags -version  +标识默认开启   开启压缩性能更好
         * 1、默认配置启动了压缩指针  12 + 4(对齐填充) 一个对象16个字节
         * 关闭压缩：java -XX:-PrintCommandLineFlags -version   配置到java虚拟机
         *  16  一个对象16个字节，没有压缩
         * **/


    }
}

class  Customer1{ //只有对象头的实例对象

}
class  Customer{  //对象头  16字节(忽略压缩指针的影响) + 4字节 + 1字节 = 21字节  --> 对齐填充， 24字节
    int id;    //实例数据
    boolean flag = false; //实例数据
}