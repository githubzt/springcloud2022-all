package com.zhangtao.springcloud.watcherModel;

/**
 * 1. @ClassDescription:  定义游资类，实现观察者接口
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 14:42
 */
public class PersonObserver implements SharsObserver{
    @Override
    public void response(int i) {
        if(i>0){
            System.out.println("游资：涨了，快点投资投资……");
        }else {
            System.out.println("游资： 跌了，快点撤资撤资……");
        }
    }
}