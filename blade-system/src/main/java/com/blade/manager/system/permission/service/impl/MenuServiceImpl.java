package com.blade.manager.system.permission.service.impl;

import com.alibaba.fastjson.JSON;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.mapper.MenuMapper;
import com.blade.manager.system.permission.model.menu.MenuTreeVO;
import com.blade.manager.system.permission.model.menu.Meta;
import com.blade.manager.system.permission.service.IMenuService;
import com.blade.manager.system.permission.service.IUserRolesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private IUserRolesService userRolesService;

    @Override
    public List<Menu> selectMenuByRoleIds(List<Integer> roleIds) {
        return super.baseMapper.selectMenuByRoleIds(roleIds);
    }

    @Override
    public List<MenuTreeVO> buildMenuTree(Long userId) {
        List<Menu> menus = this.selectMenuByRoleIds(this.userRolesService.getRoleIdsByUserId(userId));

        List<MenuTreeVO> menuTree = new ArrayList<>();

        if (CollectionUtils.isEmpty(menus)) {
            return menuTree;
        }

        menus.forEach(menu -> {
            MenuTreeVO rootNode = new MenuTreeVO();
            if (menu.getPid() == 0 && !Objects.equals(menu.getType(), "2")) {
                this.buildMenuTreeVO(rootNode, menu);
                this.buildMenuChild(rootNode, menus, menu.getId());

                rootNode.setPath("/" + rootNode.getPath());
                menuTree.add(rootNode);
            }
        });

        super.logger.info("menu tree is : " + JSON.toJSONString(menuTree));

        return menuTree;
    }

    private void buildMenuChild(MenuTreeVO parent, List<Menu> menus, Long parentId) {
        List<MenuTreeVO> children = new ArrayList<>();
        menus.forEach(menu -> {
            if (Objects.equals(menu.getPid(), parentId) && !Objects.equals(menu.getType(), "2")) {
                MenuTreeVO child = new MenuTreeVO();
                this.buildMenuTreeVO(child, menu);
                this.buildMenuChild(child, menus, menu.getId());
                children.add(child);
            }
        });
        if (!CollectionUtils.isEmpty(children)) {
            parent.setChildren(children);
        }
    }

    private void buildMenuTreeVO(MenuTreeVO menuTreeVO, Menu menu) {
        menuTreeVO.setName(menu.getMenuName());
        menuTreeVO.setPath(menu.getPath());
        if (StringUtils.isBlank(menu.getComponent())) {
            menuTreeVO.setComponent("Layout");
        } else {
            menuTreeVO.setComponent(menu.getComponent());
        }

        menuTreeVO.setMeta(new Meta(menu.getMenuName(), menu.getIcon()));
    }
}