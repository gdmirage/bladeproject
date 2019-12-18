package com.blade.manager.system.modules.permission.model.dept;

/**
 * @author blade
 * 2019/10/14 16:01
 */
public class DeptSearchDTO {

    private static final long serialVersionUID = -1266377118643775680L;

    private String name;

    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
