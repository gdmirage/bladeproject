package com.blade.manager.system.modules.permission.model.menu;

import java.io.Serializable;

/**
 * @author blade
 * 2019/10/14 16:01
 */
public class MenuListSearchDTO implements Serializable {

    private static final long serialVersionUID = 1058902797484136183L;
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
