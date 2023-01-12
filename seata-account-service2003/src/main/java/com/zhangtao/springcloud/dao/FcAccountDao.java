package com.zhangtao.springcloud.dao;

import com.zhangtao.springcloud.entities.FcAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 11:14
 */
@Mapper
public interface FcAccountDao {

    //查询库存
    FcAccount selectByUserId(String userId);

    //插入产品
    void create(FcAccount fcAccount);

    //降库存
    void updateUsed(FcAccount fcAccount);
}