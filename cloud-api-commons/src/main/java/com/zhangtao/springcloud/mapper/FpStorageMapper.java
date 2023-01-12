package com.zhangtao.springcloud.mapper;

import com.zhangtao.springcloud.entities.FpStorage;

public interface FpStorageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FpStorage record);

    int insertOrUpdate(FpStorage record);

    int insertOrUpdateSelective(FpStorage record);

    int insertSelective(FpStorage record);

    FpStorage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FpStorage record);

    int updateByPrimaryKey(FpStorage record);
}