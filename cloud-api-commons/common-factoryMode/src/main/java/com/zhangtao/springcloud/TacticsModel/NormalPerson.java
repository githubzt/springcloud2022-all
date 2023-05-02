package com.zhangtao.springcloud.TacticsModel;

/**
 * 1. @ClassDescription:  实现类 实现接口中定义的计算价格方法   普通会员类 不打折
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月24日 10:46
 */
public class NormalPerson  implements StrategyInt{

    @Override
    public double getPrice(double price, int n) {
        System.out.println("普通会员不打折……");
        return price*n;
    }

}