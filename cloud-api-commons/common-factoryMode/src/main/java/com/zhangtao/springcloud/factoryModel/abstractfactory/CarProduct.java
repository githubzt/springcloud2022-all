package com.zhangtao.springcloud.factoryModel.abstractfactory;

/**
 * 1. @ClassDescription:  解耦：分离职责，把复杂对象的创建和使⽤的过程分开。
 *          复⽤代码 降低维护成本：如果对象创建复杂且多处需⽤到，如果每处都进⾏编写，则很多重复代码，
 *          如果业务逻辑发⽣了改 变，需⽤四处修改；使⽤⼯⼚模式统⼀创建，则只要修改⼯⼚类即可， 降低成本。
 *
 *代码结构如下：
 *  以新能源汽车为例，五菱和特斯拉。
 * 定义汽车接口，汽车接口中定义了一些方法，比如启动、运行、关闭。
 * 定义特斯拉和五菱的汽车类，重写汽车接口的这些方法。
 * 定义生产汽车的工厂接口，接口中有生产汽车的方法，返回值类型为汽车接口。
 * 分别定义特斯拉和五菱的工厂类，实现生产汽车的工厂接口，重写生产汽车的方法。
 * 测试类中直接new五菱和特斯拉的工厂类，生产出相关的产品，调用启动、运行、关闭方法。
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月27日 11:03
 */
public interface CarProduct {

    /**启动**/
    void start();
    /**跑**/
    void run();
    /**停车**/
    void shutdown();


}