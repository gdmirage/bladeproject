package com.blade.manager.system.permission.api;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.RoleDepts;
import com.blade.manager.system.permission.model.RoleDeptsPageSearchDTO;
import com.blade.manager.system.permission.service.IRoleDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@RestController("ApiRoleDeptsController")
@RequestMapping("/api/permission/roleDepts")
public class RoleDeptsController extends BaseController {
    private IRoleDeptsService roleDeptsService;

    @Autowired
    public RoleDeptsController(IRoleDeptsService roleDeptsService) {
        this.roleDeptsService = roleDeptsService;
    }

    @PostMapping("/page")
    public PageInfo<RoleDepts> page(@RequestBody RoleDeptsPageSearchDTO roleDeptsPageSearchDTO) {
        return this.roleDeptsService.page(roleDeptsPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.roleDeptsService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public RoleDepts getById(@PathVariable Integer id) {
        return this.roleDeptsService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody RoleDepts roleDepts) {
        this.roleDeptsService.insert(roleDepts);
    }

    @PostMapping("/edit")
    public void update(@RequestBody RoleDepts roleDepts) {
        this.roleDeptsService.update(roleDepts);
    }
}