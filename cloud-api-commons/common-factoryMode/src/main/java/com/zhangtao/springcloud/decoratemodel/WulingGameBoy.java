package com.zhangtao.springcloud.decoratemodel;

/**
 * 1. @ClassDescription: 定义五菱新能源的具体型号，例如gameboy，继承至五菱新能源类：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月15日 19:43
 */
public class WulingGameBoy extends WulingNewEngeryCar{
    @Override
    void run() {
        System.out.println("五菱GameBoy");
    }
}