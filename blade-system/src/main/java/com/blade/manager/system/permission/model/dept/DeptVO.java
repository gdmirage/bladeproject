package com.blade.manager.system.permission.model.dept;

import com.blade.core.model.base.JsonAble;

/**
 * TODO:
 * 部门VO
 * @author Blade
 * @date 2020/1/8 9:31
 */
public class DeptVO extends JsonAble {

    private static final long serialVersionUID = 7214898954589795841L;

    private String deptName;

    private Long id;

    private Long pid;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
