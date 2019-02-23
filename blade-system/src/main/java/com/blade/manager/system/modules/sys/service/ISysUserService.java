package com.blade.manager.system.modules.sys.service;

import com.blade.manager.system.common.persistence.entity.SysUser;
import com.blade.manager.system.common.service.ICrudService;
import com.blade.manager.system.modules.sys.dao.SysUserDAO;

/**
 * Created by Administrator on 2019/1/31.
 */
public interface ISysUserService extends ICrudService<SysUserDAO, SysUser>{

    void testTransaction() throws Exception;
}
