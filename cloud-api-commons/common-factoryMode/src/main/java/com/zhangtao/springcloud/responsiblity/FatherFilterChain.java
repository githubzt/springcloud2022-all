package com.zhangtao.springcloud.responsiblity;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. @ClassDescription: 定义责任链基类，定义集合，集合中存放泛型为消息接口的对象 ，
 *                      调用责任链类的时候循环集合，调用对象的过滤方法：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月16日 22:52
 */
public class FatherFilterChain {
    /**储存http和msg的filter**/
    List<FatherFilter> fatherFilters =  new ArrayList<FatherFilter>();

    /**添加filter**/
    public void add(FatherFilter filter){
        fatherFilters.add(filter);
    }

    /**定义执行过滤的方法**/
    public void doFilter(String msg){
        for (int i = 0; i < fatherFilters.size(); i++) {
            msg = fatherFilters.get(i).doFilrter(msg);
        }
    }

}