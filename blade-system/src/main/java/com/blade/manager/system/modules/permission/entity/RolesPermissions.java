package com.blade.manager.system.modules.permission.entity;

import com.blade.manager.system.common.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public class RolesPermissions extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
