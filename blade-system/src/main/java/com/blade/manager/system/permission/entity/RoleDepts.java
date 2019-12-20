package com.blade.manager.system.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class RoleDepts extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return this.deptId;
    }

}
