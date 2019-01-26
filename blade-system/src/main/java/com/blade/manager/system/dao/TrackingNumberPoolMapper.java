package com.blade.manager.system.dao;

import com.blade.manager.system.entity.TrackingNumberPool;

public interface TrackingNumberPoolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TrackingNumberPool record);

    int insertSelective(TrackingNumberPool record);

    TrackingNumberPool selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TrackingNumberPool record);

    int updateByPrimaryKey(TrackingNumberPool record);
}