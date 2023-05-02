package com.zhangtao.springcloud.watcherModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. @ClassDescription:  定义主题类，将观察者的动态组装在一起：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 14:48
 * 定义主题类型
 *  其中定义可以新增删除 通知观察者的方法
 */
public class Subject {

    /**存储游资和基金对象**/
    List<SharsObserver>  sharsList = new ArrayList<SharsObserver>();

    /**新增观察者**/
    public void addObserver(SharsObserver sharsObserver){
        sharsList.add(sharsObserver);
    }

    /**通知观察者**/
    public void change(int j){
        for(int i=0;i<sharsList.size();i++){
            SharsObserver sharsObserver = sharsList.get(i);
            sharsObserver.response(j);
        }
    }
}