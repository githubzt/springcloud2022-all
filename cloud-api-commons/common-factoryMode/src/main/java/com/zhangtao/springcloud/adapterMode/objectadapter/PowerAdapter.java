package com.zhangtao.springcloud.adapterMode.objectadapter;

/**
 * 1. @ClassDescription: 定义适配器类，实现typeC接口，注入TypeA对象：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:08
 */
public class PowerAdapter implements TypeCInterface{

    public  TypeA typeA = new TypeA();

    public PowerAdapter(TypeA typeA){
        this.typeA = typeA;
    }

    @Override
    public void v5() {
        typeA.v220();
    }
}