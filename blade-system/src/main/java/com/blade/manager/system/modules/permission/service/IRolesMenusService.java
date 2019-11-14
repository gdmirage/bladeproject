package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.modules.permission.entity.RolesMenus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IRolesMenusService extends IService<RolesMenus> {

    /**
     * 根据角色Id删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(Long roleId);
}
