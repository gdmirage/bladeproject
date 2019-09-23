package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.modules.permission.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blade.manager.system.modules.permission.model.menu.MenuDTO;
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
public interface IMenuService extends IService<Menu> {

    List<MenuVO> getUserMenuByUsername(String username);
}
