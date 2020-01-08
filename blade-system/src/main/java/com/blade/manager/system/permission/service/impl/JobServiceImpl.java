package com.blade.manager.system.permission.service.impl;

import com.blade.core.page.PageHelper;
import com.blade.core.page.PageInfo;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.entity.Job;
import com.blade.manager.system.permission.mapper.JobMapper;
import com.blade.manager.system.permission.model.JobPageSearchDTO;
import com.blade.manager.system.permission.model.job.JobListVO;
import com.blade.manager.system.permission.service.IDeptService;
import com.blade.manager.system.permission.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("jobService")
public class JobServiceImpl extends BaseServiceImpl<JobMapper, Job> implements IJobService {

    @Autowired
    private IDeptService deptService;

    @Override
    public PageInfo<JobListVO> page(JobPageSearchDTO pageSearchDTO) {
        PageInfo<JobListVO> pageInfo = PageHelper.startPage(pageSearchDTO.getPageNumber(), pageSearchDTO.getPageSize())
                .doSelectPageInfo(() -> this.baseMapper.selectPage(pageSearchDTO)
                );

        if (!CollectionUtils.isEmpty(pageInfo.getRecordList())) {
            List<JobListVO> list = pageInfo.getRecordList();
            list.forEach(jobListVO -> {
                Dept dept = deptService.selectByPk(jobListVO.getDept().getPid());
                if (null == dept) {
                    jobListVO.setDeptSuperiorName(jobListVO.getDept().getDeptName());
                } else {
                    jobListVO.setDeptSuperiorName(dept.getDeptName());
                }
            });
        }

        return pageInfo;
    }

}