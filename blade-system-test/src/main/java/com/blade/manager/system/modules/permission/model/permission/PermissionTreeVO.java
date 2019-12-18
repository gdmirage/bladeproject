package com.blade.manager.system.modules.permission.model.permission;

import java.io.Serializable;
import java.util.List;

/**
 * @author blade
 * 2019/11/14 14:15
 */
public class PermissionTreeVO implements Serializable {

    private static final long serialVersionUID = -2239874641886971500L;

    private String label;

    private Long id;

    private List<PermissionTreeVO> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PermissionTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTreeVO> children) {
        this.children = children;
    }
}
