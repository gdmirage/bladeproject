package com.blade.manager.system.permission.model.menu;

import com.blade.core.model.base.JsonAble;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * TODO:
 * 菜单树
 *
 * @author Blade
 * @date 2020/1/4 14:31
 */
public class MenuTreeVO extends JsonAble {

    private static final long serialVersionUID = 4834974614553863894L;

    /**
     * 菜单名字
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 跳转 noredirect
     */
    private String redirect;

    /**
     * 组件
     */
    private String component;

    /**
     * 展示
     */
    private Boolean alwaysShow;

    /**
     * 标签
     */
    private Meta meta;

    /**
     * 子菜单
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuTreeVO> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<MenuTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
