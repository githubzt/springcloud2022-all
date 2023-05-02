package com.zhangtao.springcloud.circulardepend;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 20:03
 */
public class A {

    private B b;

    public B getB(){
        return b;
    }

    public void setB(B b){
        this.b=b;
    }

    public A(){
        System.out.println("---A create success");
    }
}