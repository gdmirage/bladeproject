package com.blade.manager.system.modules.permission.model.menu;

import java.io.Serializable;

/**
 * @author Blade
 * @date 2019/9/23 17:34
 */
public class MenuMetaVo implements Serializable {

    private String title;

    private String icon;

    public MenuMetaVo(String title, String icon) {
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
}
