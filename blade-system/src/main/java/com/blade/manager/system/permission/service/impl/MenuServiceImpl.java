package com.blade.manager.system.permission.service.impl;

import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.mapper.MenuMapper;
import com.blade.manager.system.permission.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> selectMenuByRoleIds(List<Integer> roleIds) {
        return super.baseMapper.selectMenuByRoleIds(roleIds);
    }
}