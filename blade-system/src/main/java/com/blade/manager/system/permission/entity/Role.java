package com.blade.manager.system.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 权限
     */
    private String permission;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getDataScope() {
        return this.dataScope;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
