package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.common.service.BaseServiceImpl;
import com.blade.manager.system.modules.permission.entity.RolesPermissions;
import com.blade.manager.system.modules.permission.mapper.RolesPermissionsMapper;
import com.blade.manager.system.modules.permission.service.IRolesPermissionsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("rolesPermissionsService")
public class RolesPermissionsServiceImpl extends BaseServiceImpl<RolesPermissionsMapper, RolesPermissions> implements IRolesPermissionsService {

    @Override
    public List<String> selectUserPermissionsByUserId(Long userId) {
        return baseMapper.selectUserPermissionsByUserId(userId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        super.baseMapper.deleteByRoleId(roleId);
    }
}
