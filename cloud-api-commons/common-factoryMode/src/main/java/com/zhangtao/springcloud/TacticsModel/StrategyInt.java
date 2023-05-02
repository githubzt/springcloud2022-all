package com.zhangtao.springcloud.TacticsModel;

/**
 * 1. @ClassDescription:  策略模式： 代码结构如下：  定义策略接口，定义通用方法。
 *      定义N个实现类，实现接口，重新方法。
 *      定义Context上下文类，利用多态进行封装。
 *      使用时通过Context上下文类进行调用，在构造函数中传入实现类的对象。
 * 策略模式：策略模式是一种行为型模式，它将对象和行为分开，将行为定义为一个行为接口 和 具体行为的实现。
 *         策略模式最大的特点是行为的变化,行为之间可以相互替换。
 * 每个if判断都可以理解为就是一个策略，可以使得算法可独立于使用它的用户而变化。
 *
 * 使用场景：
 *   1. 假设现在某超市有三个等级的会员，普通会员，VIP1，VIP2。
 *   2. 在结账的时候，三个登记的会员购买了同一种商品，普通会员不打折，VIP1打9折，VIP2打8折
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月24日 10:41
 */
public interface StrategyInt {

    //price价格 n数量
    public double getPrice(double price,int n);

}
