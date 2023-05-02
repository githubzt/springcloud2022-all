package com.zhangtao.springcloud.enjoymodel;

import java.util.HashMap;

/**
 * 1. @ClassDescription:  数据库连接工厂 生产数据库连接对象
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 14:00
 */
public class DataSourceFactory {

    private static HashMap<String, DataSource> hashMap = new HashMap<>();

    public DataSourceFactory(){
        for(int i=0;i<10;i++){
            DataSourceMaker dataSourceMaker = new DataSourceMaker(String.valueOf(i), "DataSource" + i);
            hashMap.put(dataSourceMaker.getDataId(),dataSourceMaker);
        }
    }

    public DataSource getDataSourceFactory(String dataSourceId){
        if(hashMap.containsKey(dataSourceId)){
            return hashMap.get(dataSourceId);
        }
        return null;
    }
}