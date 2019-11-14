package com.blade.manager.system.modules.permission.model.role;

import com.blade.manager.system.modules.permission.entity.Dept;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author blade
 * 2019/11/14 17:24
 */
public class RoleInsertOrUpdateVO implements Serializable{

    private static final long serialVersionUID = 7431087311825430430L;
    private Long id;

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

    private Set<Dept> depts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Dept> getDepts() {
        return depts;
    }

    public void setDepts(Set<Dept> depts) {
        this.depts = depts;
    }
}
