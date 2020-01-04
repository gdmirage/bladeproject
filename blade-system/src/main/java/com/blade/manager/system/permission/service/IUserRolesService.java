package com.blade.manager.system.permission.service;

import com.blade.manager.system.permission.entity.UserRoles;
import com.blade.core.service.IBaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IUserRolesService extends IBaseService<UserRoles> {

    /**
     * 根据用户ID获取角色ID列表
     *
     * @param userId user id
     * @return role ids
     */
    List<Integer> getRoleIdsByUserId(Long userId);
}