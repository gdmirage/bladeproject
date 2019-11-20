package com.blade.manager.system.modules.permission.controller;


import com.alibaba.fastjson.JSON;
import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.model.dept.DeptSearchDTO;
import com.blade.manager.system.modules.permission.model.dept.DeptTreeVO;
import com.blade.manager.system.modules.permission.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @PostMapping("/deptTree")
    public ResponseResult<List<DeptTreeVO>> getDeptTree(@RequestBody DeptSearchDTO deptSearchDTO) {
        System.out.println("--------->" + JSON.toJSONString(deptSearchDTO));
        return ResponseResult.ok(deptService.findDeptTree());
    }

    @GetMapping("/deptTree")
    public ResponseResult<List<DeptTreeVO>> getDeptTree() {
        return ResponseResult.ok(deptService.findDeptTree());
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        deptService.insert(dept);
        return ResponseResult.ok();
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Integer id) {
        deptService.delete(id);
        return ResponseResult.ok();
    }

    @GetMapping("/getById")
    public ResponseResult<Dept> getById(Integer id) {
        return ResponseResult.ok(200, "成功", deptService.selectByPk(id));
    }

    @PostMapping("/edit")
    public ResponseResult update(@RequestBody Dept job) {
        deptService.update(job);
        return ResponseResult.ok();
    }
}
