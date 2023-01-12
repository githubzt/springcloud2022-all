package com.zhangtao.springcloud.controller;

import com.zhangtao.springcloud.entities.CommonResult;
import com.zhangtao.springcloud.entities.FiOrder;
import com.zhangtao.springcloud.entities.FpStorage;
import com.zhangtao.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年12月30日 13:55
 */
@RestController
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/storage/checkstorage/{productId}")
    public CommonResult checkOrder(@PathVariable("productId") String productId){
        FpStorage fpStorage = storageService.selectByProductId(productId);
        return new CommonResult(200,"查询成功！",fpStorage);
    }

    @PostMapping(value = "/storage/decrease")
    public CommonResult decrease(@RequestParam("productId")String productId, @RequestParam("orderCount")Integer orderCount){

        storageService.decrease(productId,orderCount);

        return new CommonResult(200,"降库存成功！");
    }
}