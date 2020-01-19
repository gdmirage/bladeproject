package com.blade.manager.system.permission.model.menu;

import com.blade.core.model.base.JsonAble;
import com.blade.core.model.request.PageSearchDTO;

/**
 * <p>
 * 分页查询条件
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class MenuListSearchDTO extends JsonAble {
    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 菜单名称
     */
    private String menuName;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
