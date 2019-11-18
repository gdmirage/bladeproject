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
public class RolesMenus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
