package com.zhangtao.springcloud.service;

import com.zhangtao.springcloud.entities.FpStorage;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 13:56
 */
public interface StorageService {

    //查询库存
    FpStorage selectByProductId(String productId);
    //降库存
    public void decrease(String productId, Integer orderCount);

}
