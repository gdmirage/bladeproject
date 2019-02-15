package com.blade.manager.system.modules.sys.service.impl;

import com.blade.manager.system.modules.sys.dao.SysUserDAO;
import com.blade.manager.system.common.persistence.entity.SysUser;
import com.blade.manager.system.modules.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/1/31.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserDAO sysUserDAO;

    @Override
    public int insert(SysUser user) {
        return sysUserDAO.insert(user);
    }

    @Override
    public SysUser selectById(long id) {
        return sysUserDAO.selectByPK(id);
    }
}
