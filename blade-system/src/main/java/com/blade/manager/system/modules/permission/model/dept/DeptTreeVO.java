package com.blade.manager.system.modules.permission.model.dept;

import java.util.List;

/**
 * @author blade
 * 2019/10/18 11:09
 */
public class DeptTreeVO {

    private Long id;

    private String label;

    private Long pid;

    private List<DeptTreeVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<DeptTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTreeVO> children) {
        this.children = children;
    }
}
