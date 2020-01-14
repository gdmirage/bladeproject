package com.blade.manager.system.permission.model.dept;

import com.blade.core.model.base.JsonAble;

import java.time.LocalDateTime;

/**
 * <p>
 * 分页查询条件
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class DeptListSearchDTO extends JsonAble {
    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 状态(1、enabled 2、disabled)
     */
    private String status;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
