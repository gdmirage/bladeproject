package com.blade.manager.system.modules.sys.service;

import com.blade.manager.system.common.persistence.entity.SysUser;

/**
 * Created by Administrator on 2019/1/31.
 */
public interface ISysUserService {

    int insert(SysUser user);

    SysUser selectById(long id);
}
