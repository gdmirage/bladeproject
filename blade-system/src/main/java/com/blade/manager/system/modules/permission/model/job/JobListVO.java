package com.blade.manager.system.modules.permission.model.job;

import com.blade.manager.system.modules.permission.model.dept.DeptVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author blade
 * 2019/10/8 10:30
 */
public class JobListVO {

    private Long id;

    private String name;

    private Boolean enabled;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long sort;

    private Long deptId;

    private DeptVO dept;

    private String deptSuperiorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public DeptVO getDept() {
        return dept;
    }

    public void setDept(DeptVO dept) {
        this.dept = dept;
    }

    public String getDeptSuperiorName() {
        return deptSuperiorName;
    }

    public void setDeptSuperiorName(String deptSuperiorName) {
        this.deptSuperiorName = deptSuperiorName;
    }
}
