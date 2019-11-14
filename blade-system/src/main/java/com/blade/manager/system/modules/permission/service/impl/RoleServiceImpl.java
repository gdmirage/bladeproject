package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.*;
import com.blade.manager.system.modules.permission.mapper.RoleMapper;
import com.blade.manager.system.modules.permission.model.role.*;
import com.blade.manager.system.modules.permission.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IRolesDeptsService rolesDeptsService;

    @Autowired
    private IRolesPermissionsService rolesPermissionsService;

    @Autowired
    private IRolesMenusService rolesMenusService;

    @Override
    public List<Role> getRolesByUserId(long userId) {
        return baseMapper.selectRolesByUserId(userId);
    }

    @Override
    public PageInfo<RoleListVO> page(RolePageSearchDTO rolePageSearchDTO) {

        PageInfo<RoleListVO> pageInfo = PageHelper.startPage(rolePageSearchDTO.getPageNum(), rolePageSearchDTO.getPageSize())
                .doSelectPageInfo(() -> {
                            super.baseMapper.selectPage(rolePageSearchDTO);
                        }
                );

        List<RoleListVO> roles = pageInfo.getList();

        roles.forEach(role -> {
            role.setMenus(this.menuService.selectByRoleId(role.getId()));
            role.setPermissions(this.permissionService.selectByRoleId(role.getId()));
            role.setDepts(this.deptService.selectByRoleId(role.getId()));
        });

        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleInsertOrUpdateVO roleInsertOrUpdateVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleInsertOrUpdateVO, role);

        super.save(role);

        Set<Dept> depts = roleInsertOrUpdateVO.getDepts();

        depts.forEach(dept -> {
            RolesDepts rolesDepts = new RolesDepts();
            rolesDepts.setDeptId(dept.getId());
            rolesDepts.setRoleId(role.getId());
            this.rolesDeptsService.save(rolesDepts);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleInsertOrUpdateVO roleInsertOrUpdateVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleInsertOrUpdateVO, role);
        super.updateById(role);

        this.rolesDeptsService.deleteByRoleId(roleInsertOrUpdateVO.getId());

        Set<Dept> depts = roleInsertOrUpdateVO.getDepts();

        depts.forEach(dept -> {
            RolesDepts rolesDepts = new RolesDepts();
            rolesDepts.setDeptId(dept.getId());
            rolesDepts.setRoleId(role.getId());
            this.rolesDeptsService.save(rolesDepts);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long roleId) {
        this.rolesDeptsService.deleteByRoleId(roleId);
        super.removeById(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermissions(RolePermissionUpdateVO rolePermissionUpdateVO){
        this.rolesPermissionsService.deleteByRoleId(rolePermissionUpdateVO.getRoleId());
        rolePermissionUpdateVO.getPermissionIds().forEach(permissionId -> {
            RolesPermissions rolesPermissions = new RolesPermissions();
            rolesPermissions.setPermissionId(permissionId);
            rolesPermissions.setRoleId(rolePermissionUpdateVO.getRoleId());
            this.rolesPermissionsService.save(rolesPermissions);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenus(RoleMenuUpdateVO roleMenuUpdateVO){
        this.rolesMenusService.deleteByRoleId(roleMenuUpdateVO.getRoleId());
        roleMenuUpdateVO.getMenuIds().forEach(menuId -> {
            RolesMenus rolesMenus = new RolesMenus();
            rolesMenus.setMenuId(menuId);
            rolesMenus.setRoleId(roleMenuUpdateVO.getRoleId());
            this.rolesMenusService.save(rolesMenus);
        });
    }
}
