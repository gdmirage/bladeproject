package com.blade.manager.system.modules.permission.entity;

import com.blade.manager.system.common.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    private String dataScope;

    private Integer level;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

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

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
