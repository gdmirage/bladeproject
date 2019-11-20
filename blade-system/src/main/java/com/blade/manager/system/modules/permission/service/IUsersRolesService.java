package com.blade.manager.system.modules.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.UsersRoles;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IUsersRolesService extends IBaseService<UsersRoles> {

    /**
     * 根据用户ID删除角色
     *
     * @param userId 用户ID
     */
    void deleteByUserId(Long userId);
}
