package com.blade.manager.system.modules.permission.service.impl;

import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.modules.permission.entity.RolesDepts;
import com.blade.manager.system.modules.permission.mapper.RolesDeptsMapper;
import com.blade.manager.system.modules.permission.service.IRolesDeptsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-11-14
 */
@Service("rolesDeptsService")
public class RolesDeptsServiceImpl extends BaseServiceImpl<RolesDeptsMapper, RolesDepts> implements IRolesDeptsService {

    @Override
    public void deleteByRoleId(Long roleId) {
        super.baseMapper.deleteByRoleId(roleId);
    }
}
