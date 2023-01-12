package com.zhangtao.springcloud.mapper;

import com.zhangtao.springcloud.entities.FiOrder;

public interface FiOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FiOrder record);

    int insertOrUpdate(FiOrder record);

    int insertOrUpdateSelective(FiOrder record);

    int insertSelective(FiOrder record);

    FiOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FiOrder record);

    int updateByPrimaryKey(FiOrder record);
}