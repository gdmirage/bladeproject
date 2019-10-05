package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.common.BaseController;
import com.blade.manager.system.common.ResponseResult;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.service.IJobService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {

    private IJobService jobService;

    @Autowired
    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/page")
    public ResponseResult<PageInfo<Job>> page() {
        return ResponseResult.ok(200, "请求成功", jobService.page());
    }
}
