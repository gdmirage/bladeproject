package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.mapper.MenuMapper;
import com.blade.manager.system.modules.permission.model.menu.MenuDTO;
import com.blade.manager.system.modules.permission.service.IMenuService;
import com.blade.manager.system.modules.permission.service.IRoleService;
import com.blade.manager.system.modules.permission.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public List<MenuDTO> getUserMenuByUsername(String username) {
        User user = userService.getUserByNameOrEmail(username);
        List<Long> roleIds = roleService.getRolesByUserId(user.getId()).stream().map(Role::getId).distinct()
                .collect(Collectors.toList());
        List<MenuDTO> menuDTOList = baseMapper.selectByRoleIds(roleIds);
        return menus2MenuDTOTree(menuDTOList);
    }

    private List<MenuDTO> menus2MenuDTOTree(List<MenuDTO> menuDTOList) {
        List<MenuDTO> tree = new ArrayList<>();

        menuDTOList.forEach(menuDTO -> {
            if (0 == menuDTO.getPid()) {
                menuDTO = this.getChildren(menuDTO, menuDTOList);
                tree.add(menuDTO);
            }
        });

        return tree;
    }

    private MenuDTO getChildren(MenuDTO menuDTO, List<MenuDTO> menuDTOList) {
        List<MenuDTO> children = menuDTO.getChildren();

        for (MenuDTO menu : menuDTOList) {
            if (Objects.equals(menu.getPid(), menuDTO.getId())) {
                if(CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }

                children.add(menu);
                // 递归获取
                this.getChildren(menu, menuDTOList);
            }
        }
        menuDTO.setChildren(children);
        return menuDTO;
    }
}
