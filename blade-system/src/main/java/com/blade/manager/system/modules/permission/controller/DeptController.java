package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.common.ResponseResult;
import com.blade.manager.system.modules.permission.model.dept.DeptTreeVO;
import com.blade.manager.system.modules.permission.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.blade.manager.system.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author blade
 * @since 2019-10-14
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private IDeptService deptService;

    @Autowired
    public DeptController(IDeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("/deptTree")
    public ResponseResult<List<DeptTreeVO>> getDeptTree(){
        return ResponseResult.ok(deptService.findDeptTree());
    }
}
