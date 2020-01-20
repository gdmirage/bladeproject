package com.blade.manager.system.permission.service;

import com.blade.manager.system.permission.entity.RoleDepts;
import com.blade.core.service.IBaseService;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IRoleDeptsService extends IBaseService<RoleDepts> {
    /**
     * 根据角色ID删除
     *
     * @param roleId role id
     * @return delete row num
     */
    int deleteByRoleId(Long roleId);
}