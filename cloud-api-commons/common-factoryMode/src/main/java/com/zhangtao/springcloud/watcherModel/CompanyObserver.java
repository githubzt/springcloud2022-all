package com.zhangtao.springcloud.watcherModel;

/**
 * 1. @ClassDescription:   定义基金类，实现观察者接口：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 14:46
 */
public class CompanyObserver implements SharsObserver{
    @Override
    public void response(int i) {
        if(i>0){
            System.out.println("机构: 涨了，在拉点投资吧……");
        }else{
            System.out.println("机构: 跌了，稳一点 先别动……");
        }
    }
}