package com.zhangtao.springcloud.dao;

import com.zhangtao.springcloud.entities.FpStorage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 12:29
 */
@Mapper
public interface FpStorageDao {

    //插入产品
    int create(FpStorage fpStorage);

    //查询库存
    FpStorage selectByProductId(String productId);

    //降库存
    int updateUsed(FpStorage fpStorage);

}