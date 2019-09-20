package com.blade.manager.system.modules.permission.mapper;

import com.blade.manager.system.modules.permission.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRolesByUserId(@Param("userId") long userId);
}