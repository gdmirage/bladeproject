package com.blade.manager.system.modules.permission.entity;

import com.blade.manager.system.common.persistence.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author blade
 * @since 2019-10-06
 */
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 描述
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
