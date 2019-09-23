package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.mapper.RoleMapper;
import com.blade.manager.system.modules.permission.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> getRolesByUserId(long userId) {
        return baseMapper.selectRolesByUserId(userId);
    }
}
