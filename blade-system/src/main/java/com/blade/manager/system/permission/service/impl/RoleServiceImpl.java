package com.blade.manager.system.permission.service.impl;

import com.blade.core.page.PageHelper;
import com.blade.core.page.PageInfo;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.entity.RoleDepts;
import com.blade.manager.system.permission.mapper.RoleMapper;
import com.blade.manager.system.permission.model.role.RoleInsertOrUpdateVO;
import com.blade.manager.system.permission.model.role.RoleListVO;
import com.blade.manager.system.permission.model.role.RolePageSearchDTO;
import com.blade.manager.system.permission.service.IDeptService;
import com.blade.manager.system.permission.service.IMenuService;
import com.blade.manager.system.permission.service.IRoleDeptsService;
import com.blade.manager.system.permission.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleDeptsService roleDeptsService;

    @Override
    public PageInfo<RoleListVO> selectPage(RolePageSearchDTO searchDTO) {

        PageInfo<RoleListVO> pageInfo = PageHelper.startPage(searchDTO.getPageNumber(), searchDTO.getPageSize())
                .doSelectPageInfo(() -> super.baseMapper.selectPage(searchDTO));

        List<RoleListVO> roleList = pageInfo.getRecordList();

        roleList.forEach(roleListVO -> {
            roleListVO.setDepts(this.deptService.getDeptsByRoleId(roleListVO.getId()));
            roleListVO.setMenus(this.menuService.getMenusByRoleId(roleListVO.getId()));
        });

        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(RoleInsertOrUpdateVO role) {

        Long roleId = role.getId();
        this.roleDeptsService.deleteByRoleId(roleId);

        this.insertRoleDepts(role.getDeptIds(), roleId);

        return super.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(RoleInsertOrUpdateVO role) {

        int num = super.insert(role);
        this.insertRoleDepts(role.getDeptIds(), role.getId());
        return num;
    }

    private void insertRoleDepts(List<Long> deptIds, Long roleId) {
        deptIds.forEach(deptId -> {
            RoleDepts roleDepts = new RoleDepts();
            roleDepts.setDeptId(deptId);
            roleDepts.setRoleId(roleId);
            this.roleDeptsService.insert(roleDepts);
        });
    }
}