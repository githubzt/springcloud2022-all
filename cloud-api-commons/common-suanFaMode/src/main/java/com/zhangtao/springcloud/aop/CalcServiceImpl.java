package com.zhangtao.springcloud.aop;

import org.springframework.stereotype.Service;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月06日 9:57
 */
@Service
public class CalcServiceImpl  implements CalcService{

    @Override
    public int div(int x, int y) {
        int result = x/y;
        System.out.println("     =====>CalcServiceImpl被调用了，计算结果" + result);
        return result;
    }

}