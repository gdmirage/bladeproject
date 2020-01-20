package com.blade.manager.system.permission.model.menu;

import com.blade.core.model.base.JsonAble;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/1/19 16:26
 */
public class MenuListTreeVO extends JsonAble {

    private static final long serialVersionUID = -8712042190426311519L;

    private Long id;

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
     * 是否外链
     */
    private String iframe;

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

    private Integer sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuListTreeVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getIframe() {
        return iframe;
    }

    public void setIframe(String iframe) {
        this.iframe = iframe;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<MenuListTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuListTreeVO> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
