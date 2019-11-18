package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.common.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.model.menu.MenuListSearchDTO;
import com.blade.manager.system.modules.permission.model.menu.MenuListVO;
import com.blade.manager.system.modules.permission.model.menu.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 根据用户名获取菜单信息
     * @param username 用户名
     * @return
     */
    List<MenuVO> getUserMenuByUsername(String username);

    /**
     * 获取菜单树
     * @param menuListSearchDTO {@link MenuListSearchDTO}
     * @return {@link List<MenuListVO>}
     */
    List<MenuListVO> getMenuTree(MenuListSearchDTO menuListSearchDTO);

    /**
     * 根据角色获取菜单
     * @param roleId 角色ID
     * @return {@link List<Menu>}
     */
    List<Menu> selectByRoleId(Long roleId);
}
