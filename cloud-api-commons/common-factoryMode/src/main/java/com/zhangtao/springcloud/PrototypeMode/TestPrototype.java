package com.zhangtao.springcloud.PrototypeMode;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:49
 */
public class TestPrototype {

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepCopy deepCopy = new DeepCopy();
        DeepCopy copy = (DeepCopy)deepCopy.clone();
        System.out.println(deepCopy);
        System.out.println(copy);
        System.out.println(deepCopy == copy);
        deepCopy.setAge(20000);
        System.out.println(deepCopy.getAge() + " 拷贝后： " + copy.getAge());

        LowCopy lowCopy = new LowCopy();
        LowCopy clone = lowCopy.clone();
        System.out.println(lowCopy);
        System.out.println(clone);
        System.out.println(lowCopy == clone);
        lowCopy.setAge(20000);
        System.out.println(lowCopy.getAge() + " 拷贝后： " + clone.getAge());
    }
}