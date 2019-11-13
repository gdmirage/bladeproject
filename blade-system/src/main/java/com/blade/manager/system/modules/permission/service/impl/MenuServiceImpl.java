package com.blade.manager.system.modules.permission.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.mapper.MenuMapper;
import com.blade.manager.system.modules.permission.model.menu.MenuListVO;
import com.blade.manager.system.modules.permission.model.menu.MenuMetaVo;
import com.blade.manager.system.modules.permission.model.menu.MenuVO;
import com.blade.manager.system.modules.permission.service.IMenuService;
import com.blade.manager.system.modules.permission.service.IRoleService;
import com.blade.manager.system.modules.permission.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public List<MenuVO> getUserMenuByUsername(String username) {
        User user = userService.getUserByNameOrEmail(username);
        List<Long> roleIds = roleService.getRolesByUserId(user.getId()).stream().map(Role::getId).distinct()
                .collect(Collectors.toList());
        List<Menu> menuList = super.baseMapper.selectByRoleIds(roleIds);
        return menus2MenuDTOTree(menuList);
    }

    @Override
    public List<MenuListVO> getMenuTree() {
        return menus2MenuListVOTree(super.baseMapper.selectMenuList());
    }

    private List<MenuListVO> menus2MenuListVOTree(List<Menu> menuList) {
        List<MenuListVO> tree = new ArrayList<>();

        menuList.forEach(menu -> {

            MenuListVO menuListVO = new MenuListVO();

            BeanUtils.copyProperties(menu, menuListVO);

            // 一级目录
            if (0 == menu.getPid()) {
                // 递归获取下级目录
                menuListVO = this.getMenuListVOChildren(menuListVO, menuList);
                tree.add(menuListVO);
            }
        });

        this.logger.info(JSON.toJSONString(tree, SerializerFeature.DisableCircularReferenceDetect));

        return tree;
    }

    private MenuListVO getMenuListVOChildren(MenuListVO menuListVO, List<Menu> menuDTOList) {
        List<MenuListVO> children = menuListVO.getChildren();

        for (Menu menu : menuDTOList) {
            if (Objects.equals(menu.getPid(), menuListVO.getId())) {
                if (CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }
                MenuListVO child = new MenuListVO();
                BeanUtils.copyProperties(menu, child);
                children.add(child);
                // 递归获取
                this.getMenuListVOChildren(child, menuDTOList);
            }
        }

        menuListVO.setChildren(children);
        return menuListVO;
    }

    private List<MenuVO> menus2MenuDTOTree(List<Menu> menuList) {
        List<MenuVO> tree = new ArrayList<>();

        menuList.forEach(menu -> {

            MenuVO menuVO = new MenuVO();
            menuVO.setId(menu.getId());
            menuVO.setName(menu.getName());
            menuVO.setComponent(menu.getComponent());
            menuVO.setPath(menu.getPath());
            menuVO.setMeta(new MenuMetaVo(menu.getName(), menu.getIcon()));
            menuVO.setLabel(menu.getName());
            // 一级目录
            if (0 == menu.getPid()) {
                // 递归获取下级目录
                menuVO = this.getChildren(menuVO, menuList);

                if (StringUtils.isEmpty(menu.getComponent())) {
                    menuVO.setComponent("Layout");
                }
                if (!menu.getiFrame()) {
                    menuVO.setPath("/" + menu.getPath());
                    // 非外链

                    // 非外链并且有子菜单，则需要添加 /
                    if (!CollectionUtils.isEmpty(menuVO.getChildren())) {
                        menuVO.setAlwaysShow(true);
                        menuVO.setRedirect("noredirect");
                    } else {
                        // 非外链 并且没有子菜单， 则需要给默认值
                        menuVO.setPath("/index");
                    }
                } else {
                    // 外链
                    if (CollectionUtils.isEmpty(menuVO.getChildren())) {
                        MenuVO child = new MenuVO();
                        BeanUtils.copyProperties(menuVO, child);
                        List<MenuVO> children = new ArrayList<>();
                        children.add(child);
                        menuVO.setChildren(children);
                    }
                }

                tree.add(menuVO);
            }
        });

        this.logger.info(JSON.toJSONString(tree, SerializerFeature.DisableCircularReferenceDetect));

        return tree;
    }

    private MenuVO getChildren(MenuVO menuVO, List<Menu> menuDTOList) {
        List<MenuVO> children = menuVO.getChildren();

        for (Menu menu : menuDTOList) {
            if (Objects.equals(menu.getPid(), menuVO.getId())) {
                if (CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }
                MenuVO child = new MenuVO();
                child.setId(menu.getId());
                child.setName(menu.getName());
                child.setPath(menu.getPath());
                child.setComponent(menu.getComponent());
                child.setMeta(new MenuMetaVo(menu.getName(), menu.getIcon()));
                child.setLabel(menu.getName());
                children.add(child);
                // 递归获取
                this.getChildren(child, menuDTOList);
            }
        }

//        if (CollectionUtils.isEmpty(children)) {
//            menuVO.setAlwaysShow(true);
//            menuVO.setRedirect("noredirect");
//        }

        menuVO.setChildren(children);
        return menuVO;
    }
}
