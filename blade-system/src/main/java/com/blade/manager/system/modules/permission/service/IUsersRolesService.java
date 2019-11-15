package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.modules.permission.entity.UsersRoles;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IUsersRolesService extends IService<UsersRoles> {

    /**
     * 根据用户ID删除角色
     *
     * @param userId 用户ID
     */
    void deleteByUserId(Long userId);
}
