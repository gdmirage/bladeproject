package com.blade.manager.system.permission.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.service.IDeptService;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.model.DeptPageSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@RestController
@RequestMapping("/permission/dept")
public class DeptController extends BaseController {
    private IDeptService deptService;

    @Autowired
    public DeptController (IDeptService deptService) {
        this.deptService = deptService;
    }

    @PostMapping("/page")
    public PageInfo<Dept> page(@RequestBody DeptPageSearchDTO deptPageSearchDTO) {
        return this.deptService.page(deptPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.deptService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Dept getById(@PathVariable Integer id) {
        return this.deptService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Dept dept) {
        this.deptService.insert(dept);
    }

    @PostMapping("/edit")
    public void update(@RequestBody Dept dept) {
        this.deptService.update(dept);
    }
}