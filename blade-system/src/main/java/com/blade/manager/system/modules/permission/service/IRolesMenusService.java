package com.blade.manager.system.modules.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.RolesMenus;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IRolesMenusService extends IBaseService<RolesMenus> {

    /**
     * 根据角色Id删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(Long roleId);
}
