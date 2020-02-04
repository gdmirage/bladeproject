package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.service.IUserRolesService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.UserRoles;
import com.blade.manager.system.permission.mapper.UserRolesMapper;
import com.blade.core.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("userRolesService")
public class UserRolesServiceImpl extends BaseServiceImpl<UserRolesMapper, UserRoles> implements IUserRolesService {

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return super.baseMapper.selectRoleIdsByUserId(userId);
    }
}