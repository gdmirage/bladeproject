package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.modules.permission.entity.RolesDepts;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-11-14
 */
public interface IRolesDeptsService extends IService<RolesDepts> {

    /**
     * 根据角色id 删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(Long roleId);
}
