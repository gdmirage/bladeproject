package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.model.dept.DeptListSearchDTO;
import com.blade.manager.system.permission.model.dept.DeptVO;
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

    /**
     * 查询部门列表
     *
     * @param searchDTO {@link DeptListSearchDTO}
     * @return {@link List<Dept>}
     */
    List<Dept> selectList(@Param("searchDTO") DeptListSearchDTO searchDTO);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId role id
     * @return {@link List<DeptVO>}
     */
    List<DeptVO> getDeptsByRoleId(@Param("roleId") Long roleId);
}