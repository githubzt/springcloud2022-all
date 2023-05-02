package com.zhangtao.springcloud.facadePattem;

/**
 * 1. @ClassDescription:   门面模式（Facade Pattern）:也叫外观模式，要求一个子系统的外部与其内部的通信必须通过一个
 *          统一的对象进行。 门面模式提供一个高层次的接口，使得子系统更易于使用。
 * 适用场景：多个子系统类相互之间存在强依赖的关系，通过门面类统一处理复杂关系的调用，外部类调用时，可以调用门面类，无需再调用子系统类。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:24
 */
public class SystemA {
    //模拟系统类A  假设系统类中有方法doA
    public String doA(){
        return "A";
    }
}