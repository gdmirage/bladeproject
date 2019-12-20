package com.blade.manager.system.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class Menu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 组件
     */
    private String component;

    /**
     * 链接地址
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 上级菜单ID
     */
    private Long pid;

    /**
     * 是否隐藏
     */
    private String hidden;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 类型
     */
    private String type;

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getComponent() {
        return this.component;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getHidden() {
        return this.hidden;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
