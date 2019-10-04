package com.blade.manager.system.modules.permission.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blade.manager.system.modules.permission.service.IJobService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.blade.manager.system.common.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
@RestController
@RequestMapping("/job/job")
public class JobController extends BaseController {

    private IJobService jobService;

}
