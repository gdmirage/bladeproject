package com.blade.manager.system.modules.permission.model.role;

import java.io.Serializable;
import java.util.Set;

public class RolePermissionUpdateVO implements Serializable {

    private Long roleId;

    private Set<Long> permissionIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Set<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
