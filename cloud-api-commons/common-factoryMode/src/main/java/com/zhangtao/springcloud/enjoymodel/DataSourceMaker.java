package com.zhangtao.springcloud.enjoymodel;

/**
 * 1. @ClassDescription:  定义数据库对象生产者类：
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 13:54
 */
public class DataSourceMaker extends DataSource{

    public DataSourceMaker(String dataId, String dataName) {
        super(dataId, dataName);
    }

    @Override
    public void method() {
        System.out.println("使用datasourceMaker1生产数据库连接对象……");
    }
}