package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.common.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.RolesDepts;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-11-14
 */
public interface IRolesDeptsService extends IBaseService<RolesDepts> {

    /**
     * 根据角色id 删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(Long roleId);
}
