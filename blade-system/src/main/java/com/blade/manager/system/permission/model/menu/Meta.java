package com.blade.manager.system.permission.model.menu;

import com.blade.core.model.base.JsonAble;

/**
 * TODO:
 * 标签
 * @author Blade
 * @date 2020/1/4 14:37
 */
public class Meta extends JsonAble {
    private static final long serialVersionUID = 5136452607611266861L;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否无缓存
     */
    private Boolean noCache;

    public Meta(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getNoCache() {
        return noCache;
    }

    public void setNoCache(Boolean noCache) {
        this.noCache = noCache;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
