package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.model.dept.DeptVO;
import com.blade.manager.system.permission.service.IRoleDeptsService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.RoleDepts;
import com.blade.manager.system.permission.mapper.RoleDeptsMapper;
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
@Service("roleDeptsService")
public class RoleDeptsServiceImpl extends BaseServiceImpl<RoleDeptsMapper, RoleDepts> implements IRoleDeptsService {
    @Override
    public int deleteByRoleId(Long roleId) {
        return super.baseMapper.deleteByRoleId(roleId);
    }
}