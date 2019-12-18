package com.blade.manager.system.modules.permission.model.user;

import com.blade.core.model.request.PageSearchDTO;

/**
 * @author blade
 * 2019/11/15 10:16
 */
public class UserPageSearchDTO extends PageSearchDTO {
    private static final long serialVersionUID = -6489021103621072254L;

    private String name;

    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
