package com.blade.manager.system.modules.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 别名
     */
    private String alias;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级权限
     */
    private Integer pid;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
