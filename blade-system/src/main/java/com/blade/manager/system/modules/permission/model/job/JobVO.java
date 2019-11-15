package com.blade.manager.system.modules.permission.model.job;

import java.io.Serializable;

/**
 * @author blade
 * 2019/11/15 10:40
 */
public class JobVO implements Serializable {
    private static final long serialVersionUID = -3573236992391996927L;

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
