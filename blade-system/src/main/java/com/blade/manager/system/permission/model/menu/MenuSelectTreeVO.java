package com.blade.manager.system.permission.model.menu;

import com.blade.core.model.base.JsonAble;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/1/19 17:36
 */
public class MenuSelectTreeVO extends JsonAble {

    private static final long serialVersionUID = -1872407903373663889L;

    private Long id;

    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuSelectTreeVO> children;

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

    public List<MenuSelectTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuSelectTreeVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
