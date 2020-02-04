package com.blade.manager.system.permission.service;

import com.blade.core.page.PageInfo;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.model.role.EditMenuDTO;
import com.blade.manager.system.permission.model.role.RoleInsertOrUpdateVO;
import com.blade.manager.system.permission.model.role.RoleListVO;
import com.blade.manager.system.permission.model.role.RolePageSearchDTO;

import java.util.List;

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

    /**
     * 更新
     *
     * @param role {@link RoleInsertOrUpdateVO}
     * @return update row num
     */
    int update(RoleInsertOrUpdateVO role);

    /**
     * 新增
     *
     * @param role {@link RoleInsertOrUpdateVO}
     * @return insert row num
     */
    int insert(RoleInsertOrUpdateVO role);

    /**
     * 编辑角色菜单
     *
     * @param editMenuDTO {@link EditMenuDTO}
     */
    void editMenus(EditMenuDTO editMenuDTO);

    /**
     * 根据ID查询角色
     *
     * @param roleId 角色id
     * @return {@link RoleListVO}
     */
    RoleListVO selectById(Long roleId);

    /**
     * 查询所有的角色
     *
     * @return {@link List <Role>}
     */
    List<Role> selectAll();

    /**
     * 根据用户ID获取用户的角色等级
     *
     * @param userId 用户ID
     * @return {@link Integer} role level
     */
    Integer getRoleLevelByUserId(Long userId);
}