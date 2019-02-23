package com.blade.manager.system.modules.sys.service.impl;

import com.blade.manager.system.common.persistence.entity.SysUser;
import com.blade.manager.system.common.service.CrudServiceImpl;
import com.blade.manager.system.modules.sys.dao.SysUserDAO;
import com.blade.manager.system.modules.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/1/31.
 */
@Service("sysUserService")
public class SysUserServiceImpl extends CrudServiceImpl<SysUserDAO, SysUser> implements ISysUserService{

    @Autowired
    private SysUserDAO sysUserDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void testTransaction() throws Exception {
        SysUser user = new SysUser();
        user.setUserName("雅芳4");
        user.setLoginName("avon");
        sysUserDAO.insert(user);
        SysUser blade = selectByPK(1);
        blade.setUserName("222");
        sysUserDAO.update(blade);
        throw new Exception("test transaction");
    }

}
