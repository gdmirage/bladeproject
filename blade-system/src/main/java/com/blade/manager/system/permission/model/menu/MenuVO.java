package com.blade.manager.system.permission.model.menu;

import com.blade.core.model.base.JsonAble;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/1/20 15:34
 */
public class MenuVO extends JsonAble {

    private static final long serialVersionUID = -4574681547128277570L;

    private Long id;

    private String menuName;

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

    @Override
    public String toString() {
        return super.toString();
    }
}
