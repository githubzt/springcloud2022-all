package com.zhangtao.springcloud.TacticsModel;

/**
 * 1. @ClassDescription: 测试类  演示策略模式的使用场景
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月24日 11:16
 */
public class StrategyTest {

    public static void main(String[] args) {
        //定义三个类型对象
        NormalPerson normalPerson = new NormalPerson();
        Vip1Persion vip1Persion = new Vip1Persion();
        Vip2Persion vip2Persion = new Vip2Persion();
        //new context类对象 将三个类型的对象传入
        PersonContext personContext = new PersonContext(normalPerson);
        PersonContext personContext1 = new PersonContext(vip1Persion);
        PersonContext personContext2 = new PersonContext(vip2Persion);
        //利用多态 通过context类对象的计算价格方法 实际上调用的子类的计算价格方法 得到最终价格
        System.out.println("普通会员: " + personContext.getPrice(300, 20));
        System.out.println("VIP1: " + personContext1.getPrice(300, 20));
        System.out.println("VIP2: " + personContext2.getPrice(300, 20));

    }
}