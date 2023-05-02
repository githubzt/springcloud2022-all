package com.zhangtao.springcloud.decoratemodel;

/**
 * 1. @ClassDescription: 定义五菱续航版本的装饰类 ，继承自五菱汽车的装饰类：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 20:01
 */
public class RunLongDecorate extends WulingDecorate{
    /**
     * 调用父类的构造方法
     * @param wulingNewEngeryCar
     **/
    public RunLongDecorate(WulingNewEngeryCar wulingNewEngeryCar) {
        super(wulingNewEngeryCar);
    }

    @Override
    void run(){
        super.run();
        System.out.println("续航增强");
    }
}