package com.zhangtao.springcloud.juc.atomicAPI;

import java.util.concurrent.atomic.*;

/**
 * 1. @ClassDescription: 原子操作类之18罗汉增强：  天生保证原子性，底层是CAS思想+unsafe类，不用Synchronized重锁 即可保证原子性
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月08日 15:31
 */
public class baseAPIDemo {

    public static void main(String[] args) {
        //基本类型
        AtomicInteger aInteger = new AtomicInteger();
        AtomicBoolean aBoolean = new AtomicBoolean();
        AtomicLong aLong = new AtomicLong();
        //数组类型
        AtomicIntegerArray integerArray = new AtomicIntegerArray(10);
        AtomicLongArray longArray = new AtomicLongArray(10);
        AtomicReferenceArray<Object> referenceArray = new AtomicReferenceArray<>(10);

        AtomicIntegerArray integerArray1 = new AtomicIntegerArray(new int[5]);
        AtomicIntegerArray integerArray2 = new AtomicIntegerArray(5);
        AtomicIntegerArray integerArray3 = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});

        for (int i = 0; i < integerArray1.length(); i++) {
            System.out.println("案例一: " +integerArray1.get(i));
        }
        for (int i = 0; i < integerArray2.length(); i++) {
            System.out.println("案例二: " + integerArray2.get(i));
        }
        for (int i = 0; i < integerArray3.length(); i++) {
            System.out.println("案例三: " + integerArray3.get(i));
        }

        int tmpInt = 0;
        tmpInt = integerArray3.getAndSet(0, 1122);
        System.out.println(tmpInt + "\t" + integerArray3.get(0));

        tmpInt = integerArray3.getAndIncrement(0);
        System.out.println(tmpInt + "\t" + integerArray3.get(0));

        //引用类型
        AtomicReference<Object> objectAtomicReference = new AtomicReference<>();
        AtomicStampedReference<Object> objectAReference = new AtomicStampedReference<>("1",1);
        AtomicMarkableReference<Object> markableReference = new AtomicMarkableReference<>("1",true);


        // & 与运算  与运算&：遇0则全为0，双1才为1
        int i1 = 2;
        String s1 = Integer.toBinaryString(i1);
        System.out.println("&与 运算："+s1);
        int i2 = 1;
        String s2 = Integer.toBinaryString(i2);
        System.out.println(s2);

        int i3 = i1&i2;
        String s3 = Integer.toBinaryString(i3);
        System.out.println(s3);
        System.out.println(i1&i2);


    }

}