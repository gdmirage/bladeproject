package com.blade.manager.system.modules.permission.model.job;

import com.blade.core.annotation.Encrypt;
import com.blade.core.model.request.PageSearchDTO;

/**
 * @author blade
 * 2019/10/14 16:01
 */
public class JobPageSearchDTO extends PageSearchDTO {

    private static final long serialVersionUID = -1266377118643775680L;

    @Encrypt
    private String name;

    private Boolean enabled;

    private Long deptId;

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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
