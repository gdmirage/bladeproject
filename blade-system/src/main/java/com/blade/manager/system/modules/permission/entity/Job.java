package com.blade.manager.system.modules.permission.entity;

import com.blade.manager.system.common.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
public class Job extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Boolean enabled;

    private LocalDateTime createTime;

    private Long sort;

    private Long deptId;

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
}
