package com.zhangtao.springcloud.TacticsModel;

/**
 * 1. @ClassDescription:  定义context上下文类，利用多态进行封装：
 *              对实现类和接口进行封装
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月24日 10:57
 */
public class PersonContext {
    //定义私有对象
    private StrategyInt strategyInt;

    //定义有参构造
    public PersonContext(StrategyInt strategyInt){
        this.strategyInt = strategyInt;
    }

    //定义计算价格的方法
    public double getPrice(double price,int n){
        return strategyInt.getPrice(price,n);
    }
}