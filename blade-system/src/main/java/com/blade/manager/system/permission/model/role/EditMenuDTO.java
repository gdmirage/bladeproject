package com.blade.manager.system.permission.model.role;

import com.blade.core.model.base.JsonAble;

import java.util.List;

/**
 * TODO:
 * 编辑角色菜单
 *
 * @author blade
 * 2020-01-31 11:32
 */
public class EditMenuDTO extends JsonAble {
    private static final long serialVersionUID = 5234784452930650944L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID列表
     */
    private List<Long> menuIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
