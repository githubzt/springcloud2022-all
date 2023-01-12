package com.zhangtao.springcloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 1. @ClassDescription: 使用seata代理数据源
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 14:11
 */
@Configuration
public class DataSourceProxyConfig {

    @Bean  //作用：加载application.properties文件中的数据库连接参数
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 需要将 DataSourceProxy 设置为主数据源，否则事务无法回滚
     * @param druidDataSource The DruidDataSource
     * @return The default datasource
     */
    @Bean("dataSource")
    @Primary
    public DataSource dataSource(DruidDataSource druidDataSource){
        return new DataSourceProxy(druidDataSource);
    }
}