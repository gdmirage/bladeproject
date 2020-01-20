package com.blade.manager.system.permission.service.impl;

import com.blade.core.page.PageInfo;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.enums.MenuEnums;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.mapper.MenuMapper;
import com.blade.manager.system.permission.model.menu.MenuListSearchDTO;
import com.blade.manager.system.permission.model.menu.MenuListTreeVO;
import com.blade.manager.system.permission.model.menu.MenuSelectTreeVO;
import com.blade.manager.system.permission.model.menu.MenuTreeVO;
import com.blade.manager.system.permission.model.menu.Meta;
import com.blade.manager.system.permission.service.IMenuService;
import com.blade.manager.system.permission.service.IUserRolesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    public PageInfo<MenuListTreeVO> getDeptListTree(MenuListSearchDTO searchDTO) {
        List<Menu> menuList = super.baseMapper.selectList(searchDTO);

        // 为了迎合前端的统一封装，转成pageInfo的格式
        PageInfo<MenuListTreeVO> pageInfo = new PageInfo<>();
        pageInfo.setPageNumber(0);
        pageInfo.setPageSize(9999);
        List<MenuListTreeVO> deptListTreeList = this.buildMenuListTree(menuList);
        pageInfo.setRecordList(deptListTreeList);
        pageInfo.setTotalCount(deptListTreeList.size());
        return pageInfo;
    }

    private List<MenuListTreeVO> buildMenuListTree(List<Menu> menuList) {
        List<MenuListTreeVO> menuListTreeVOList = new ArrayList<>();

        if (this.containsRootId(menuList)) {
            menuListTreeVOList = this.buildMenuListTree(menuList, 0L);
        } else {
            menuListTreeVOList = this.buildMenuList(menuList);
        }

        return menuListTreeVOList;
    }

    /**
     * 判断是否有root 节点的ID
     *
     * @param menuList {@link List<Menu>}
     * @return true or false
     */
    private boolean containsRootId(List<Menu> menuList) {
        for (Menu dept : menuList) {
            if (dept.getPid() == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 构建列表树形结构
     *
     * @param menuList {@link List<Menu>}
     * @param parentId pid
     * @return {@link List<MenuListTreeVO>}
     */
    private List<MenuListTreeVO> buildMenuListTree(List<Menu> menuList, Long parentId) {
        List<MenuListTreeVO> menuListTreeVOList = new ArrayList<>();

        if (CollectionUtils.isEmpty(menuList)) {
            return menuListTreeVOList;
        }

        menuList.forEach(dept -> {
            if (Objects.equals(dept.getPid(), parentId)) {
                // 根节点
                MenuListTreeVO rootNode = this.menu2MenuListTreeVO(dept);

                List<MenuListTreeVO> children = this.buildMenuListTree(menuList, dept.getId());
                if (!CollectionUtils.isEmpty(children)) {
                    rootNode.setChildren(children);
                }
                menuListTreeVOList.add(rootNode);
            }
        });

        return menuListTreeVOList;
    }

    /**
     * 构建列表
     *
     * @param menuList {@link List<Menu>}
     * @return {@link List<MenuListTreeVO>}
     */
    private List<MenuListTreeVO> buildMenuList(List<Menu> menuList) {
        List<MenuListTreeVO> menuListTreeVOList = new ArrayList<>();

        menuList.forEach(dept -> {
            menuListTreeVOList.add(this.menu2MenuListTreeVO(dept));
        });

        return menuListTreeVOList;
    }

    private MenuListTreeVO menu2MenuListTreeVO(Menu menu) {
        MenuListTreeVO menuListTreeVO = new MenuListTreeVO();

        BeanUtils.copyProperties(menu, menuListTreeVO);

        return menuListTreeVO;
    }

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
            if (menu.getPid() == 0 && !Objects.equals(menu.getType(), MenuEnums.MenuTypeEnum.BUTTON.getCode())) {
                this.buildMenuTreeVO(rootNode, menu);
                this.buildMenuChild(rootNode, menus, menu.getId());

                rootNode.setPath("/" + rootNode.getPath());
                menuTree.add(rootNode);
            }
        });

        return menuTree;
    }

    private void buildMenuChild(MenuTreeVO parent, List<Menu> menus, Long parentId) {
        List<MenuTreeVO> children = new ArrayList<>();
        menus.forEach(menu -> {
            if (Objects.equals(menu.getPid(), parentId) && !Objects.equals(menu.getType(), MenuEnums.MenuTypeEnum.BUTTON.getCode())) {
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

    @Override
    public List<MenuSelectTreeVO> buildMenuSelectTree() {
        List<Menu> menus = super.baseMapper.selectList(new MenuListSearchDTO());

        List<MenuSelectTreeVO> menuSelectTreeVOList = new ArrayList<>();

        menus.forEach(menu -> {
            MenuSelectTreeVO rootNode = new MenuSelectTreeVO();
            if (menu.getPid() == 0) {

                rootNode.setId(menu.getId());
                rootNode.setLabel(menu.getMenuName());

                this.buildMenuSelectChild(rootNode, menus, menu.getId());

                menuSelectTreeVOList.add(rootNode);
            }
        });

        return menuSelectTreeVOList;
    }

    private void buildMenuSelectChild(MenuSelectTreeVO parent, List<Menu> menus, Long parentId) {
        List<MenuSelectTreeVO> children = new ArrayList<>();
        menus.forEach(menu -> {
            if (Objects.equals(menu.getPid(), parentId)) {
                MenuSelectTreeVO child = new MenuSelectTreeVO();
                child.setLabel(menu.getMenuName());
                child.setId(menu.getId());
                this.buildMenuSelectChild(child, menus, menu.getId());
                children.add(child);
            }
        });
        if (!CollectionUtils.isEmpty(children)) {
            parent.setChildren(children);
        }
    }
}