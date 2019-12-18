package com.blade.manager.system.modules.permission.model.menu;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author blade
 * 2019/9/23 11:41
 */
public class MenuDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 是否外链
     */
    private Boolean iFrame;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 组件
     */
    private String component;

    /**
     * 上级菜单ID
     */
    private Long pid;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接地址
     */
    private String path;

    /**
     * 子菜单
     */
    private List<MenuDTO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getiFrame() {
        return iFrame;
    }

    public void setiFrame(Boolean iFrame) {
        this.iFrame = iFrame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }
}
