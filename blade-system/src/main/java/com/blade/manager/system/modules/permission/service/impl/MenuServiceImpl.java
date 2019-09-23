package com.blade.manager.system.modules.permission.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.mapper.MenuMapper;
import com.blade.manager.system.modules.permission.model.menu.MenuDTO;
import com.blade.manager.system.modules.permission.model.menu.MenuMetaVo;
import com.blade.manager.system.modules.permission.model.menu.MenuVO;
import com.blade.manager.system.modules.permission.service.IMenuService;
import com.blade.manager.system.modules.permission.service.IRoleService;
import com.blade.manager.system.modules.permission.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public List<MenuVO> getUserMenuByUsername(String username) {
        User user = userService.getUserByNameOrEmail(username);
        List<Long> roleIds = roleService.getRolesByUserId(user.getId()).stream().map(Role::getId).distinct()
                .collect(Collectors.toList());
        List<Menu> menuList = baseMapper.selectByRoleIds(roleIds);
        return menus2MenuDTOTree(menuList);
    }

    private List<MenuVO> menus2MenuDTOTree(List<Menu> menuList) {
        List<MenuVO> tree = new ArrayList<>();

        menuList.forEach(menu -> {

            MenuVO menuVO = new MenuVO();
            menuVO.setId(menu.getId());
            menuVO.setName(menu.getName());
            menuVO.setPath(menu.getPath());

            if (0 == menu.getPid()) {
                menuVO = this.getChildren(menuVO, menuList);
                if (StringUtils.isEmpty(menu.getComponent())) {
                    menu.setComponent("Layout");
                }

                if (!menu.getiFrame()) {
                    menu.setPath("/" + menu.getPath());
                }

                menuVO.setMeta(new MenuMetaVo(menu.getName(), menu.getIcon()));

                tree.add(menuVO);
            }
        });

        System.out.println(JSON.toJSONString(tree, SerializerFeature.DisableCircularReferenceDetect));

        return tree;
    }

    private MenuVO getChildren(MenuVO menuVO, List<Menu> menuDTOList) {
        List<MenuVO> children = menuVO.getChildren();

        for (Menu menu : menuDTOList) {
            if (Objects.equals(menu.getPid(), menuVO.getId())) {
                if(CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }
                MenuVO child = new MenuVO();
                child.setName(menu.getName());
                child.setPath(menu.getPath());
                child.setMeta(new MenuMetaVo(menu.getName(), menu.getIcon()));
                children.add(child);
                // 递归获取
                this.getChildren(child, menuDTOList);
            }
        }
        menuVO.setChildren(children);
        return menuVO;
    }
}
