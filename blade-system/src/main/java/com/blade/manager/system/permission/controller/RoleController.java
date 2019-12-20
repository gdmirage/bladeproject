package com.blade.manager.system.permission.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.service.IRoleService;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.model.RolePageSearchDTO;
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
@RequestMapping("/permission/role")
public class RoleController extends BaseController {
    private IRoleService roleService;

    @Autowired
    public RoleController (IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/page")
    public PageInfo<Role> page(@RequestBody RolePageSearchDTO rolePageSearchDTO) {
        return this.roleService.page(rolePageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.roleService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Role getById(@PathVariable Integer id) {
        return this.roleService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Role role) {
        this.roleService.insert(role);
    }

    @PostMapping("/edit")
    public void update(@RequestBody Role role) {
        this.roleService.update(role);
    }
}