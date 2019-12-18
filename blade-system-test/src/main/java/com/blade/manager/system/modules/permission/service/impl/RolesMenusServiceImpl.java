package com.blade.manager.system.modules.permission.service.impl;

import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.modules.permission.entity.RolesMenus;
import com.blade.manager.system.modules.permission.mapper.RolesMenusMapper;
import com.blade.manager.system.modules.permission.service.IRolesMenusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("rolesMenusService")
public class RolesMenusServiceImpl extends BaseServiceImpl<RolesMenusMapper, RolesMenus> implements IRolesMenusService {

    @Override
    public void deleteByRoleId(Long roleId) {
        this.baseMapper.deleteByRoleId(roleId);
    }
}
