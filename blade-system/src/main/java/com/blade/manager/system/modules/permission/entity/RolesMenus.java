package com.blade.manager.system.modules.permission.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blade.manager.system.common.BaseEntity;

/**
 * <p>
 *
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
    @TableField("menu_id")
    private Long menuId;

    /**
     * 角色ID
     */
    @TableField("role_id")
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
