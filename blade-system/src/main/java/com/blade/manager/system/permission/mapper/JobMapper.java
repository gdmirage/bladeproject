package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.Job;
import com.blade.manager.system.permission.model.JobPageSearchDTO;
import com.blade.manager.system.permission.model.job.JobListVO;
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
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 岗位列表查询
     *
     * @param searchDTO {@link JobPageSearchDTO}
     * @return {@link List<JobListVO>}
     */
    List<JobListVO> selectPage(@Param("searchDTO") JobPageSearchDTO searchDTO);
}