package com.blade.generator.dao;

import com.blade.generator.entity.TrackingNumberPool;

public interface TrackingNumberPoolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TrackingNumberPool record);

    int insertSelective(TrackingNumberPool record);

    TrackingNumberPool selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TrackingNumberPool record);

    int updateByPrimaryKey(TrackingNumberPool record);
}