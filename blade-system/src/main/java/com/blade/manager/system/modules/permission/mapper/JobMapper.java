package com.blade.manager.system.modules.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.model.job.JobListVO;

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

    List<JobListVO> selectPage();
}
