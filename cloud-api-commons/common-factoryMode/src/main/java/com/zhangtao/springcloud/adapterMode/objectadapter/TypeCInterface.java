package com.zhangtao.springcloud.adapterMode.objectadapter;

/**
 * 1. @ClassDescription: 适配器模式  将一个类的接口转换成客户希望的另外一个接口。
 *           Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作
 *          所谓适配器，就是将不同类型的接口利用某种方法组装在一起，例如我们生活中遇到的数据线转化器，能将不同类型的接口
 *          转换为我们需要的接口，适配器模式一般应用于新老项目并存的情况，能将新老项目中不同写法的代码转换。
 * 对象适配器模式
 *  注入对象，完成适配器转换，代码结构如下：
 *       定义接口TYPEC
 *       定义类TYPEA
 *       定义适配类，实现接口TYPEC
 *      填充TypeA的对象为属性，重写接口的方法
 *      在接口的方法中可以调用typeA的方法
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:01
 */
public interface TypeCInterface {

    /**输出5V电压**/
    public void v5();
}
