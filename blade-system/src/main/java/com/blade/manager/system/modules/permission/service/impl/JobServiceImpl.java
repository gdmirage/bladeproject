package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.common.service.BaseServiceImpl;
import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.mapper.JobMapper;
import com.blade.manager.system.modules.permission.model.job.JobListVO;
import com.blade.manager.system.modules.permission.model.job.JobPageSearchDTO;
import com.blade.manager.system.modules.permission.service.IDeptService;
import com.blade.manager.system.modules.permission.service.IJobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
@Service("jobService")
public class JobServiceImpl extends BaseServiceImpl<JobMapper, Job> implements IJobService {

    @Autowired
    private IDeptService deptService;

    @Override
    public PageInfo<JobListVO> page(JobPageSearchDTO jobPageSearchDTO) {

        super.pageTest(jobPageSearchDTO);

        PageInfo<JobListVO> pageInfo = PageHelper.startPage(jobPageSearchDTO.getPageNum(), jobPageSearchDTO.getPageSize())
                .doSelectPageInfo(() -> {
                            baseMapper.selectPage(jobPageSearchDTO);
                        }
                );

        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            List<JobListVO> list = pageInfo.getList();
            list.forEach(jobListVO -> {
                Dept dept = deptService.selectByPk(jobListVO.getDept().getPid());
                if (null == dept) {
                    jobListVO.setDeptSuperiorName(jobListVO.getDept().getName());
                } else {
                    jobListVO.setDeptSuperiorName(dept.getName());
                }
            });
        }

        return pageInfo;
    }
}
