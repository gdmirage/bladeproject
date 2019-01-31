package com.blade.manager.system.service;

import com.blade.manager.system.entity.SysUser;

/**
 * Created by Administrator on 2019/1/31.
 */
public interface ISysUserService {

    SysUser selectById(long id);
}
