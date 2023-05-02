package com.zhangtao.springcloud.decoratemodel;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:56
 */
public class NoDoorDecorate extends WulingDecorate{
    /**
     * 调用父类的构造方法
     * @param wulingNewEngeryCar
     **/
    public NoDoorDecorate(WulingNewEngeryCar wulingNewEngeryCar) {
        super(wulingNewEngeryCar);
    }

    @Override
    void run(){
        super.run();
        System.out.println("增加敞篷功能");
    }

}