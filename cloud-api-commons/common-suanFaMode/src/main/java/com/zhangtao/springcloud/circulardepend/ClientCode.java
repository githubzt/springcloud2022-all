package com.zhangtao.springcloud.circulardepend;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 20:07
 */
public class ClientCode {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        b.setA(a);
        a.setB(b);
    }


}