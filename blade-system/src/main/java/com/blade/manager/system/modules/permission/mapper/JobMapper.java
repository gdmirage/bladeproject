package com.blade.manager.system.modules.permission.mapper;

import com.blade.manager.system.common.persistence.BaseMapper;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.model.job.JobListVO;
import com.blade.manager.system.modules.permission.model.job.JobPageSearchDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
public interface JobMapper extends BaseMapper<Job> {

    List<JobListVO> selectPage(@Param("searchDTO") JobPageSearchDTO searchDTO);
}
