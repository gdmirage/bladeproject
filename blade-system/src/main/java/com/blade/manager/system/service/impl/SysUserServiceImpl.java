package com.blade.manager.system.service.impl;

import com.blade.manager.system.dao.SysUserMapper;
import com.blade.manager.system.entity.SysUser;
import com.blade.manager.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/1/31.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser selectById(long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
