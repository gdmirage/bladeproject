package com.blade.manager.system.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.RoleMenus;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IRoleMenusService extends IBaseService<RoleMenus> {

    /**
     * 根据角色ID删除菜单
     *
     * @param roleId 角色ID
     * @return delete row num
     */
    int deleteByRoleId(Long roleId);
}