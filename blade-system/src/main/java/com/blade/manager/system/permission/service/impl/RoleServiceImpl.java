package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.service.IRoleService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.mapper.RoleMapper;
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

}