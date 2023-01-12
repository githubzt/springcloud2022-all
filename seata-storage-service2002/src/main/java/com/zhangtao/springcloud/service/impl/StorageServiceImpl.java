package com.zhangtao.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhangtao.springcloud.dao.FpStorageDao;
import com.zhangtao.springcloud.entities.FpStorage;
import com.zhangtao.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 13:56
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private FpStorageDao fpStorageDao;

    @Override
    public FpStorage selectByProductId(String productId) {
        log.info("---------查询库存开始：" + productId);
        FpStorage fpStorage = fpStorageDao.selectByProductId(productId);
        log.info("---------库存查询结果： "+ JSONObject.toJSONString(fpStorage));
        return fpStorage;
    }

    @Override
    public void decrease(String productId, Integer orderCount) {

        log.info("查询库存入参productId： "+productId +"\t orderCount: "+orderCount);
        //查询库存
        FpStorage fpStorage = fpStorageDao.selectByProductId(productId);
        log.info("查询库存结果： "+ JSONObject.toJSONString(fpStorage));

        Integer used = fpStorage.getProductUsed()+orderCount;
        Integer resid = fpStorage.getProductTotal()-used;

        //库存计算 降库存
        FpStorage fpStorage1 = new FpStorage();
        fpStorage1.setProductId(productId);
        fpStorage1.setProductUsed(used);
        fpStorage1.setProductResidue(resid);
        fpStorageDao.updateUsed(fpStorage1);
    }
}