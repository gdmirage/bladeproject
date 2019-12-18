package com.blade.manager.system.modules.permission.model.role;

import java.io.Serializable;
import java.util.Set;

public class RoleMenuUpdateVO implements Serializable {

    private Long roleId;

    private Set<Long> menuIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Set<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
