package com.blade.manager.system.modules.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author blade
 * @since 2019-11-14
 */
public class RolesDepts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long deptId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
