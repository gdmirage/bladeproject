package com.blade.manager.system.permission.service.impl;

import com.blade.core.page.PageHelper;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.model.role.RoleListVO;
import com.blade.manager.system.permission.model.role.RolePageSearchDTO;
import com.blade.manager.system.permission.service.IDeptService;
import com.blade.manager.system.permission.service.IMenuService;
import com.blade.manager.system.permission.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.mapper.RoleMapper;
import com.blade.core.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * <p>
 *  服务实现类
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
}