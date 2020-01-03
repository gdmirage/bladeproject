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
public class UserRoles extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
