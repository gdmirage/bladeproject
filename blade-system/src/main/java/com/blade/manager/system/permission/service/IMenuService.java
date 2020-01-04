package com.blade.manager.system.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.model.menu.MenuTreeVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 根据角色ID获取menu
     *
     * @param roleIds 角色ID
     * @return menus
     */
    List<Menu> selectMenuByRoleIds(List<Integer> roleIds);

    /**
     * 获取左侧菜单
     * @param userId 用户ID
     * @return {@link MenuTreeVO}
     */
    List<MenuTreeVO> buildMenuTree(Long userId);
}