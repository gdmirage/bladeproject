package com.blade.manager.system.modules.permission.model.role;

import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.entity.Permission;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author blade
 * 2019/11/14 15:11
 */
public class RoleListVO implements Serializable {

    private static final long serialVersionUID = 5098039338759672410L;
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

    private List<Permission> permissions;

    private List<Menu> menus;

    private List<Dept> depts;

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Dept> getDepts() {
        return depts;
    }

    public void setDepts(List<Dept> depts) {
        this.depts = depts;
    }
}
