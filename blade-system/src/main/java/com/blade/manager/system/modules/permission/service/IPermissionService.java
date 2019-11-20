package com.blade.manager.system.modules.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.Permission;
import com.blade.manager.system.modules.permission.model.permission.PermissionListVO;
import com.blade.manager.system.modules.permission.model.permission.PermissionSearchDTO;
import com.blade.manager.system.modules.permission.model.permission.PermissionTreeVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IPermissionService extends IBaseService<Permission> {

    /**
     * 获取权限列表
     *
     * @param permissionSearchDTO {@link PermissionSearchDTO}
     * @return {@link List<PermissionListVO>}
     */
    List<PermissionListVO> getPermissionList(PermissionSearchDTO permissionSearchDTO);

    /**
     * 获取权限树
     *
     * @return {@link List<PermissionTreeVO>}
     */
    List<PermissionTreeVO> getPermissionTree();

    /**
     * 根据角色获取权限
     *
     * @param roleId 角色id
     * @return {@link List<Permission>}
     */
    List<Permission> selectByRoleId(Long roleId);
}
