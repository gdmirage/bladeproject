package com.blade.manager.system.modules.permission.service.impl;

import com.blade.core.page.PageHelper;
import com.blade.core.page.PageInfo;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.entity.RolesDepts;
import com.blade.manager.system.modules.permission.entity.RolesMenus;
import com.blade.manager.system.modules.permission.entity.RolesPermissions;
import com.blade.manager.system.modules.permission.mapper.RoleMapper;
import com.blade.manager.system.modules.permission.model.role.RoleInsertOrUpdateVO;
import com.blade.manager.system.modules.permission.model.role.RoleListVO;
import com.blade.manager.system.modules.permission.model.role.RoleMenuUpdateVO;
import com.blade.manager.system.modules.permission.model.role.RolePageSearchDTO;
import com.blade.manager.system.modules.permission.model.role.RolePermissionUpdateVO;
import com.blade.manager.system.modules.permission.service.IDeptService;
import com.blade.manager.system.modules.permission.service.IMenuService;
import com.blade.manager.system.modules.permission.service.IPermissionService;
import com.blade.manager.system.modules.permission.service.IRoleService;
import com.blade.manager.system.modules.permission.service.IRolesDeptsService;
import com.blade.manager.system.modules.permission.service.IRolesMenusService;
import com.blade.manager.system.modules.permission.service.IRolesPermissionsService;
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
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

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
    public RoleListVO getRoleById(Long roleId) {
        Role role = super.selectByPk(roleId);
        RoleListVO roleListVO = new RoleListVO();
        BeanUtils.copyProperties(role, roleListVO);
        roleListVO.setMenus(this.menuService.selectByRoleId(roleId));
        roleListVO.setPermissions(this.permissionService.selectByRoleId(roleId));
        roleListVO.setDepts(this.deptService.selectByRoleId(roleId));

        return roleListVO;
    }

    @Override
    public List<Role> getRolesByUserId(long userId) {
        return baseMapper.selectRolesByUserId(userId);
    }

    @Override
    public PageInfo<RoleListVO> page(RolePageSearchDTO rolePageSearchDTO) {

        PageInfo<RoleListVO> pageInfo = PageHelper.startPage(rolePageSearchDTO.getPageNumber(), rolePageSearchDTO.getPageSize())
                .doSelectPageInfo(() -> {
                            super.baseMapper.selectPage(rolePageSearchDTO);
                        }
                );

        List<RoleListVO> roles = pageInfo.getRecordList();

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

        super.insert(role);

        Set<Dept> depts = roleInsertOrUpdateVO.getDepts();

        depts.forEach(dept -> {
            RolesDepts rolesDepts = new RolesDepts();
            rolesDepts.setDeptId(dept.getId());
            rolesDepts.setRoleId(role.getId());
            this.rolesDeptsService.insert(rolesDepts);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleInsertOrUpdateVO roleInsertOrUpdateVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleInsertOrUpdateVO, role);
        super.update(role);

        this.rolesDeptsService.deleteByRoleId(roleInsertOrUpdateVO.getId());

        Set<Dept> depts = roleInsertOrUpdateVO.getDepts();

        depts.forEach(dept -> {
            RolesDepts rolesDepts = new RolesDepts();
            rolesDepts.setDeptId(dept.getId());
            rolesDepts.setRoleId(role.getId());
            this.rolesDeptsService.insert(rolesDepts);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long roleId) {
        this.rolesDeptsService.deleteByRoleId(roleId);
        super.delete(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermissions(RolePermissionUpdateVO rolePermissionUpdateVO) {
        this.rolesPermissionsService.deleteByRoleId(rolePermissionUpdateVO.getRoleId());
        rolePermissionUpdateVO.getPermissionIds().forEach(permissionId -> {
            RolesPermissions rolesPermissions = new RolesPermissions();
            rolesPermissions.setPermissionId(permissionId);
            rolesPermissions.setRoleId(rolePermissionUpdateVO.getRoleId());
            this.rolesPermissionsService.insert(rolesPermissions);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenus(RoleMenuUpdateVO roleMenuUpdateVO) {
        this.rolesMenusService.deleteByRoleId(roleMenuUpdateVO.getRoleId());
        roleMenuUpdateVO.getMenuIds().forEach(menuId -> {
            RolesMenus rolesMenus = new RolesMenus();
            rolesMenus.setMenuId(menuId);
            rolesMenus.setRoleId(roleMenuUpdateVO.getRoleId());
            this.rolesMenusService.insert(rolesMenus);
        });
    }
}
