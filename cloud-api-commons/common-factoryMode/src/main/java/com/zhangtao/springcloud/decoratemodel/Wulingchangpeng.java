package com.zhangtao.springcloud.decoratemodel;

/**
 * 1. @ClassDescription:  定义五菱新能源的具体型号，例如敞篷版，继承至五菱新能源类：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:46
 */
public class Wulingchangpeng extends WulingNewEngeryCar{
    @Override
    void run() {
        System.out.println("敞篷版五菱");
    }
}