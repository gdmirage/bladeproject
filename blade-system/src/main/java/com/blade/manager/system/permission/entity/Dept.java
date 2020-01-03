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
public class Dept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 上级部门ID
     */
    private Long pid;

    /**
     * 状态(1、enabled 2、disabled
     */
    private String status;

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
