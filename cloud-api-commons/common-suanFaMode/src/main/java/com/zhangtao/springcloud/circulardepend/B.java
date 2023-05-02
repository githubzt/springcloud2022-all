package com.zhangtao.springcloud.circulardepend;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年02月28日 20:03
 */
public class B {

    private A a;

    public A getA(){
        return a;
    }

    public void setA(A a){
        this.a = a;
    }

    public B(){
        System.out.println("---B create success");
    }

}