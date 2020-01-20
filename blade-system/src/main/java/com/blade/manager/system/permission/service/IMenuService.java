package com.blade.manager.system.permission.service;

import com.blade.core.page.PageInfo;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.model.menu.MenuListSearchDTO;
import com.blade.manager.system.permission.model.menu.MenuListTreeVO;
import com.blade.manager.system.permission.model.menu.MenuSelectTreeVO;
import com.blade.manager.system.permission.model.menu.MenuTreeVO;
import com.blade.manager.system.permission.model.menu.MenuVO;

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
     * 获取菜单树形列表
     *
     * @param searchDTO {@link MenuListSearchDTO}
     * @return {@link PageInfo<MenuListTreeVO>}
     */
    PageInfo<MenuListTreeVO> getDeptListTree(MenuListSearchDTO searchDTO);

    /**
     * 根据角色ID获取menu
     *
     * @param roleIds 角色ID
     * @return menus
     */
    List<Menu> selectMenuByRoleIds(List<Integer> roleIds);

    /**
     * 获取左侧菜单
     *
     * @param userId 用户ID
     * @return {@link MenuTreeVO}
     */
    List<MenuTreeVO> buildMenuTree(Long userId);

    /**
     * 获取下拉框
     *
     * @return {@link List<MenuSelectTreeVO>}
     */
    List<MenuSelectTreeVO> buildMenuSelectTree();

    /**
     * 根据角色ID 获取菜单
     *
     * @param roleId role id
     * @return {@link List<MenuVO>}
     */
    List<MenuVO> getMenusByRoleId(Long roleId);
}