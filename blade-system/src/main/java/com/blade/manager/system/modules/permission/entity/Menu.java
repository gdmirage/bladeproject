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
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
}
