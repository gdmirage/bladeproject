package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 查询所有的启用的部门
     *
     * @param status 状态
     * @return {@link List<Dept>}
     */
    List<Dept> findAllEnabledDept(@Param("status") String status);
}