package com.zhangtao.springcloud.adapterMode.classadapter;

/**
 * 1. @ClassDescription:  可以看到，这两种实现模式最大的差别在于，一个是注入对象实现接口，
 *               一个是继承类实现接口，在使用过程中，一般推荐第一种方式，继承在某种程度上来说，会加重类之间的耦合度。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 17:17
 */
public class PowerAdapter extends TypeA implements TypeCInterface{
    @Override //建议使用
    public void v5() {
        this.v220();
    }

    @Override  //不建议使用  继承在某种程度上来说，会加重类之间的耦合度。
    public void v220(){
        super.v220();
    }
}