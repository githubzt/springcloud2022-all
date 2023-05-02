package com.zhangtao.springcloud.decoratemodel;

/**
 * 1. @ClassDescription: 我们可以看到，装饰器模式的优势在于，封装不变的run方法，然后基于run方法进行增强，
 * 满足在不同场景下针对某些方法进行不同方式的增强，假如又新增了一个型号，
 * 那么只需要新增型号类和具体的装饰器类即可，大大减少了代码的耦合，同时又满足方法的增强。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 20:03
 */
public class AppTestClass {

    public static void main(String[] args) {
        //new 一个wuling 敞篷类
        Wulingchangpeng wulingchangpeng = new Wulingchangpeng();
        //调用敞篷装饰器  增加敞篷功能
        NoDoorDecorate noDoorDecorate = new NoDoorDecorate(wulingchangpeng);
        noDoorDecorate.run();
        RunLongDecorate runLongDecorate = new RunLongDecorate(wulingchangpeng);
        runLongDecorate.run();
        System.out.println("*****************************");
        WulingGameBoy wulingGameBoy = new WulingGameBoy();
        RunLongDecorate runLongDecorate1 = new RunLongDecorate(wulingGameBoy);
        runLongDecorate1.run();
    }

}