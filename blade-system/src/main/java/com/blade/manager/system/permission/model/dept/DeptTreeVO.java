package com.blade.manager.system.permission.model.dept;

import com.blade.core.model.base.JsonAble;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * TODO:
 * 部门树结构
 *
 * @author Blade
 * @date 2020/1/9 17:02
 */
public class DeptTreeVO extends JsonAble {
    private static final long serialVersionUID = -4340338832090327630L;

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String label;

    /**
     * 子部门
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    public List<DeptTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTreeVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
