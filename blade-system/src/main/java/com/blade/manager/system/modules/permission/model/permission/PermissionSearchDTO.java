package com.blade.manager.system.modules.permission.model.permission;

import java.io.Serializable;

/**
 * @author blade
 * 2019/11/14 10:21
 */
public class PermissionSearchDTO implements Serializable {

    private String alias;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
