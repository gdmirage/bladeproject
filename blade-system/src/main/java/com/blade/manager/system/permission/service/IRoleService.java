package com.blade.manager.system.permission.service;

import com.blade.core.page.PageInfo;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.model.role.RoleListVO;
import com.blade.manager.system.permission.model.role.RolePageSearchDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IRoleService extends IBaseService<Role> {
    /**
     * 分页查询
     *
     * @param searchDTO {@link RolePageSearchDTO}
     * @return {@link PageInfo<RoleListVO>}
     */
    PageInfo<RoleListVO> selectPage(RolePageSearchDTO searchDTO);
}