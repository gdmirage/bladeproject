package com.blade.manager.system.permission.model.dept;

import com.blade.core.model.base.JsonAble;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO:
 * 部门列表树形结构
 * @author Blade
 * @date 2020/1/14 16:24
 */
public class DeptListTreeVO extends JsonAble {

    private static final long serialVersionUID = 3204205423862281850L;

    /**
     * id
     */
    private Long id;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 状态
     */
    private String status;

    /**
     * 部门名
     */
    private String deptName;

    /**
     * 标签名
     */
    private String label;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private List<DeptListTreeVO> children;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<DeptListTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<DeptListTreeVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
