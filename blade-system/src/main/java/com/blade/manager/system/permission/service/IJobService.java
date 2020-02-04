package com.blade.manager.system.permission.service;

import com.blade.core.page.PageInfo;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.Job;
import com.blade.manager.system.permission.model.job.JobPageSearchDTO;
import com.blade.manager.system.permission.model.job.JobListVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IJobService extends IBaseService<Job> {

    /**
     * 分页查询列表
     *
     * @param pageSearchDTO {@link JobPageSearchDTO}
     * @return {@link PageInfo<JobListVO>}
     */
    PageInfo<JobListVO> page(JobPageSearchDTO pageSearchDTO);
}