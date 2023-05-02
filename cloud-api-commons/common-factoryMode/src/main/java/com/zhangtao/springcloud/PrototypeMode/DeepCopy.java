package com.zhangtao.springcloud.PrototypeMode;

import java.io.*;

/**
 * 1. @ClassDescription:   而深拷贝就是利用IO流将对象copy一份出来，在修改任意对象的属性时，另一对象的属性不会发生变化。
 * 深拷贝的实现结构如下：
 *     定义悟空类，定义属性、构造方法、tostring方法、实现cloneable接口
 *     将当前对象写入流中，并通过流再次写出对象。
 *     此时修改对象2.对象1的属性不会跟着变化了。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:24
 */
public class DeepCopy implements Cloneable, Serializable {
    private String name = "孙悟空";
    private int age = 5000;
    private String skill="七十二变";

    public DeepCopy(){}

    public DeepCopy(String name, int age, String skill){
        this.name = name; this.age = age; this.skill = skill;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //将对象写入字节流
            ObjectOutputStream outputStream1 = new ObjectOutputStream(outputStream);
            outputStream1.writeObject(this);
            //把字节流转化为对象
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream inputStream1 = new ObjectInputStream(inputStream);
            System.out.println("走进来了……");
            DeepCopy deepCopy = (DeepCopy) inputStream1.readObject();
            return deepCopy;
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
            return null;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "deepCopy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skill='" + skill + '\'' +
                '}';
    }
}