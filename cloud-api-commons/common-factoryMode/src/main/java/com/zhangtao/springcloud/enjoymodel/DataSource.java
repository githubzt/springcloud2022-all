package com.zhangtao.springcloud.enjoymodel;

/**
 * 1. @ClassDescription: 享元模式： 通过共享已经存在的对象来大幅度减少需要创建的对象数量、避免大量相似对象的开销，
 *  从而提高系统资源的利用率，将重复出现的内容作为共享部分取出，由多个对象共享一份，从而减轻内存的压力
 * 应用场景：String的实现就是享元模式，底层有针对各种字符的常量池，有变量引用到常量时，就直接引用常量池中的常量，
 *          又例如数据库连接池，也是利用享元模式。
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月17日 11:16
 */
public abstract class DataSource {
    /**定义数据库连接资源基类 其他类需要继承此类**/
    String dataId;
    String dataName;

    public DataSource(String dataId,String dataName){
        this.dataId = dataId;
        this.dataName = dataName;
    }

    public abstract void method();


    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "dataId='" + dataId + '\'' +
                ", dataName='" + dataName + '\'' +
                '}';
    }
}