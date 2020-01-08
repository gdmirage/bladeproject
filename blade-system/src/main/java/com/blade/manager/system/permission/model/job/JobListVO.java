package com.blade.manager.system.permission.model.job;

import com.blade.core.model.base.JsonAble;
import com.blade.manager.system.permission.model.dept.DeptVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * TODO:
 * 岗位列表VO
 *
 * @author Blade
 * @date 2020/1/7 16:09
 */
public class JobListVO extends JsonAble {

    private static final long serialVersionUID = -1485332302129296186L;

    /**
     * id
     */
    private Long id;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 状态(1、enabled 2、disabled)
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 上级部门名称
     */
    private String deptSuperiorName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 部门
     */
    private DeptVO dept;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDeptSuperiorName() {
        return deptSuperiorName;
    }

    public void setDeptSuperiorName(String deptSuperiorName) {
        this.deptSuperiorName = deptSuperiorName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public DeptVO getDept() {
        return dept;
    }

    public void setDept(DeptVO dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
