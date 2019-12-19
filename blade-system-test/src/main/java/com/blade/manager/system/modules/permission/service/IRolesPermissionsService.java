package com.blade.manager.system.modules.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.RolesPermissions;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IRolesPermissionsService extends IBaseService<RolesPermissions> {

    /**
     * 获取用户的权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectUserPermissionsByUserId(Long userId);

    /**
     * 根据角色Id删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(Long roleId);
}