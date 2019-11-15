package com.blade.manager.system.modules.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.model.role.RoleInsertOrUpdateVO;
import com.blade.manager.system.modules.permission.model.role.RoleListVO;
import com.blade.manager.system.modules.permission.model.role.RoleMenuUpdateVO;
import com.blade.manager.system.modules.permission.model.role.RolePageSearchDTO;
import com.blade.manager.system.modules.permission.model.role.RolePermissionUpdateVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据id获取
     *
     * @param roleId 角色id
     * @return {@link RoleListVO}
     */
    RoleListVO getRoleById(Long roleId);

    List<Role> getRolesByUserId(long userId);

    PageInfo<RoleListVO> page(RolePageSearchDTO rolePageSearchDTO);

    /**
     * 新增
     *
     * @param roleInsertOrUpdateVO {@link RoleInsertOrUpdateVO}
     */
    void add(RoleInsertOrUpdateVO roleInsertOrUpdateVO);

    /**
     * 更新
     *
     * @param roleInsertOrUpdateVO {@link RoleInsertOrUpdateVO}
     */
    void update(RoleInsertOrUpdateVO roleInsertOrUpdateVO);

    /**
     * 删除
     *
     * @param roleId 角色Id
     */
    void delete(Long roleId);

    /**
     * 更新角色权限
     *
     * @param rolePermissionUpdateVO {@link RolePermissionUpdateVO}
     */
    void updatePermissions(RolePermissionUpdateVO rolePermissionUpdateVO);

    /**
     * 更新角色菜单
     *
     * @param roleMenuUpdateVO {@link RoleMenuUpdateVO}
     */
    void updateMenus(RoleMenuUpdateVO roleMenuUpdateVO);
}
