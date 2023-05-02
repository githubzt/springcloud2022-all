package com.zhangtao.springcloud.juc.objecthead;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * 1. @ClassDescription: 小工具  分析对象在JVM的大小和分布
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月13日 16:13
 */
public class JOLDemo {

    public static void main(String[] args) {
        //Thread.currentThread();  当前线程
        System.out.println(VM.current().details());  //当前虚拟机详细信息
        System.out.println(VM.current().objectAlignment()); //对象的对齐数

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer3 customer3 = new Customer3();
        System.out.println(ClassLayout.parseInstance(customer3).toPrintable());

        Customer4 customer4 = new Customer4();
        System.out.println(ClassLayout.parseInstance(customer4).toPrintable());

    }
}
class Customer3{  //对象头  16字节(忽略压缩指针的影响) + 4字节 + 1字节 = 21字节  --> 对齐填充， 24字节
}

class Customer2{  //对象头  16字节(忽略压缩指针的影响) + 4字节 + 1字节 = 21字节  --> 对齐填充， 24字节
    int id;    //实例数据
    boolean flag = false; //实例数据

}

class Customer4{  //对象头  16字节(忽略压缩指针的影响) + 4字节 + 1字节 = 21字节  --> 对齐填充， 24字节
    int id;    //实例数据
    boolean flag = false; //实例数据
    long flag2 = 1L;

}