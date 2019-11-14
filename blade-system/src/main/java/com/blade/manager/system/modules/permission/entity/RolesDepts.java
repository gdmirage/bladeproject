package com.blade.manager.system.modules.permission.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blade.manager.system.common.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author blade
 * @since 2019-11-14
 */
public class RolesDepts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private Long roleId;

    @TableField("dept_id")
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
