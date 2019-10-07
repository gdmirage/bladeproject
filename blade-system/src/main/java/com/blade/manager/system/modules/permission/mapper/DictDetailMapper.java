package com.blade.manager.system.modules.permission.mapper;

import com.blade.manager.system.modules.permission.entity.DictDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-10-06
 */
public interface DictDetailMapper extends BaseMapper<DictDetail> {


    List<DictDetail> selectDictDetailByDictName (@Param("name") String name);
}
