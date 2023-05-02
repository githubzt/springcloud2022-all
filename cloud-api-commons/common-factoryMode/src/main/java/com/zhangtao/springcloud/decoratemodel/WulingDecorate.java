package com.zhangtao.springcloud.decoratemodel;

/**
 * 1. @ClassDescription: 定义五菱新能源汽车的装饰类，装饰类也继承自新能源类：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:48
 */
public class WulingDecorate extends WulingNewEngeryCar{
    /**私有的对象**/
    private WulingNewEngeryCar wulingNewEngeryCar;
    /**公共构造函数**/
    public WulingDecorate(WulingNewEngeryCar wulingNewEngeryCar){
        this.wulingNewEngeryCar = wulingNewEngeryCar;
    }
    /**重写汽车能力**/
    @Override
    void run() {
        wulingNewEngeryCar.run();
    }
}