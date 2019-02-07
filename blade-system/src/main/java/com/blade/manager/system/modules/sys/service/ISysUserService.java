package com.blade.manager.system.modules.sys.service;

import com.blade.manager.system.modules.sys.entity.SysUser;

/**
 * Created by Administrator on 2019/1/31.
 */
public interface ISysUserService {

    SysUser selectById(long id);
}
