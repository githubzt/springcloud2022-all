package com.zhangtao.springcloud.mapper;

import com.zhangtao.springcloud.entities.FcAccount;

public interface FcAccountMapper{
    int insertOrUpdate(FcAccount record);

    int insertOrUpdateSelective(FcAccount record);
}