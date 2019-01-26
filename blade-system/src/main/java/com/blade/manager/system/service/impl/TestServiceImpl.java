package com.blade.manager.system.service.impl;

import com.blade.manager.system.dao.TrackingNumberPoolMapper;
import com.blade.manager.system.entity.TrackingNumberPool;
import com.blade.manager.system.service.TestService;
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
