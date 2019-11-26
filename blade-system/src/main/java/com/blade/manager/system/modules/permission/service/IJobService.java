package com.blade.manager.system.modules.permission.service;


import com.blade.core.page.Page;
import com.blade.core.page.PageInfo;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.model.job.JobListVO;
import com.blade.manager.system.modules.permission.model.job.JobPageSearchDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
public interface IJobService extends IBaseService<Job> {
    PageInfo<JobListVO> pageList(JobPageSearchDTO jobPageSearchDTO);
}
