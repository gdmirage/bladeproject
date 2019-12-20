package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.service.IMenuService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.mapper.MenuMapper;
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

}