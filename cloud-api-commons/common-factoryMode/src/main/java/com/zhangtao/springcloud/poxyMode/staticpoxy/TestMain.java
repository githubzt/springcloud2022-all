package com.zhangtao.springcloud.poxyMode.staticpoxy;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 16:05
 */
public class TestMain {
    public static void main(String[] args) {
        CarPoxy carPoxy = new CarPoxy(new Car());
        carPoxy.run();
    }
}