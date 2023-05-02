package com.zhangtao.springcloud.juc.syncUp;

import org.openjdk.jol.info.ClassLayout;

/**
 * 1. @ClassDescription: 锁升级 代码演示
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月14日 10:45
 */
public class SynchronizedUpDemo {

    public static void main(String[] args) {
        /**  OFFSET  SIZE   TYPE DESCRIPTION  VALUE
         0     4        (object header)       01 77 2f ea (00000001 01110111 00101111 11101010) (-365988095)
         4     4        (object header)       72 00 00 00 (01110010 00000000 00000000 00000000) (114)
         *  对象标记MarkWord怎么解读： 从下网上  ，从右往左  ，单独一个8位 从前往后看
         */

        Object o = new Object();
        System.out.println("10进制：" + o.hashCode());
        System.out.println("16进制：" + Integer.toHexString(o.hashCode()));
        System.out.println("2进制：" + Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}