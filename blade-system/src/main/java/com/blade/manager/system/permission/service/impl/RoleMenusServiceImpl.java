package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.service.IRoleMenusService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.RoleMenus;
import com.blade.manager.system.permission.mapper.RoleMenusMapper;
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("roleMenusService")
public class RoleMenusServiceImpl extends BaseServiceImpl<RoleMenusMapper, RoleMenus> implements IRoleMenusService {

}