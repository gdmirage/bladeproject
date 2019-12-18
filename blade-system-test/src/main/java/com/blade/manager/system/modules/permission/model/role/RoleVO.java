package com.blade.manager.system.modules.permission.model.role;

import java.io.Serializable;

/**
 * @author blade
 * 2019/11/15 16:17
 */
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 8968964703469742484L;

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
