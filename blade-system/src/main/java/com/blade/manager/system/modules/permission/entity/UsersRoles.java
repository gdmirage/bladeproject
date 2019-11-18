package com.blade.manager.system.modules.permission.entity;

import com.blade.manager.system.common.persistence.entity.BaseEntity;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public class UsersRoles extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
