package com.blade.manager.system.modules.permission.mapper;

import com.blade.manager.system.modules.permission.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.model.dept.DeptTreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-10-14
 */
public interface DeptMapper extends BaseMapper<Dept> {

    List<DeptTreeVO> selectDeptByPid(@Param("pid") long pid);
}
