package com.blade.manager.system.modules.sys.service.impl;

import com.blade.manager.system.modules.sys.dao.SysUserMapper;
import com.blade.manager.system.modules.sys.entity.SysUser;
import com.blade.manager.system.modules.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/1/31.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectById(long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
