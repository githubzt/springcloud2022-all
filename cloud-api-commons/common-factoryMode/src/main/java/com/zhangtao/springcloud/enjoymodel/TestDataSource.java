package com.zhangtao.springcloud.enjoymodel;

/**
 * 1. @ClassDescription: 可以看到，享元模式的主旨在于构建一个池子的概念，需要使用对象的时候就从池子中去拿，
 *              无需多次创建对象，在数据库连接池的实现中可以看到其身影。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 14:19
 */
public class TestDataSource {

    public static void main(String[] args) {
        //取出数据库连接对象
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        for (int i = 0; i < 10; i++) {
            System.out.println("i: " + dataSourceFactory.getDataSourceFactory(String.valueOf(i)).toString());
        }
    }
}