package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.modules.permission.entity.Permission;
import com.blade.manager.system.modules.permission.mapper.PermissionMapper;
import com.blade.manager.system.modules.permission.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
