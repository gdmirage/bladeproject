package com.blade.manager.system.permission.model.role;

import com.blade.manager.system.permission.entity.Role;

import java.util.List;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/1/20 16:35
 */
public class RoleInsertOrUpdateVO extends Role {

    private static final long serialVersionUID = 4556650300919580998L;

    private List<Long> deptIds;

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
