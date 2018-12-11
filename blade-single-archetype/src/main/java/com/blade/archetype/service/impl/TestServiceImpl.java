package com.blade.archetype.service.impl;

import com.blade.archetype.dao.TrackingNumberPoolMapper;
import com.blade.archetype.entity.TrackingNumberPool;
import com.blade.archetype.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/11 10:32
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TrackingNumberPoolMapper trackingNumberPoolMapper;

    public TrackingNumberPool getTrackingNumberPool(long id) {
        return trackingNumberPoolMapper.selectByPrimaryKey(id);
    }
}
