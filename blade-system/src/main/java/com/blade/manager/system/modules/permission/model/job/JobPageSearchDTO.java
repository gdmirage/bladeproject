package com.blade.manager.system.modules.permission.model.job;

import com.blade.manager.system.common.model.request.PageSearchDTO;

/**
 * @author blade
 * 2019/10/14 16:01
 */
public class JobPageSearchDTO extends PageSearchDTO {

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
