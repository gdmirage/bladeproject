package com.blade.manager.system.permission.model.role;

import com.blade.core.model.base.JsonAble;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.model.dept.DeptVO;
import com.blade.manager.system.permission.model.menu.MenuVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/1/20 15:32
 */
public class RoleListVO extends JsonAble {

    private static final long serialVersionUID = -6215951907026110782L;

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 权限
     */
    private String permission;

    private List<MenuVO> menus;

    private List<DeptVO> depts;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<MenuVO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuVO> menus) {
        this.menus = menus;
    }

    public List<DeptVO> getDepts() {
        return depts;
    }

    public void setDepts(List<DeptVO> depts) {
        this.depts = depts;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
