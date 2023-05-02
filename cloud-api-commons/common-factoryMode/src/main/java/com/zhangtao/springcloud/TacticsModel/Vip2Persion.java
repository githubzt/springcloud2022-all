package com.zhangtao.springcloud.TacticsModel;

/**
 * 1. @ClassDescription: 实现类2 实现接口中定义的计算价格方法 VIP2会员类 打8折
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月24日 10:51
 */
public class Vip2Persion implements StrategyInt{
    @Override
    public double getPrice(double price, int n) {
        System.out.println("Vip2会员打8折……");
        return (price*n)*0.8;
    }
}