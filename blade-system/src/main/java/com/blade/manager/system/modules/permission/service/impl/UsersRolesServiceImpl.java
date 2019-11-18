package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.common.service.BaseServiceImpl;
import com.blade.manager.system.modules.permission.entity.UsersRoles;
import com.blade.manager.system.modules.permission.mapper.UsersRolesMapper;
import com.blade.manager.system.modules.permission.service.IUsersRolesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("usersRolesService")
public class UsersRolesServiceImpl extends BaseServiceImpl<UsersRolesMapper, UsersRoles> implements IUsersRolesService {

    @Override
    public void deleteByUserId(Long userId) {
        super.baseMapper.deleteByUserId(userId);
    }
}
