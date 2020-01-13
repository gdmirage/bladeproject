package com.blade.manager.system.permission.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.RoleMenus;
import com.blade.manager.system.permission.model.RoleMenusPageSearchDTO;
import com.blade.manager.system.permission.service.IRoleMenusService;
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
@RestController
@RequestMapping("/permission/roleMenus")
public class RoleMenusController extends BaseController {
    private IRoleMenusService roleMenusService;

    @Autowired
    public RoleMenusController(IRoleMenusService roleMenusService) {
        this.roleMenusService = roleMenusService;
    }

    @PostMapping("/page")
    public PageInfo<RoleMenus> page(@RequestBody RoleMenusPageSearchDTO roleMenusPageSearchDTO) {
        return this.roleMenusService.page(roleMenusPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.roleMenusService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public RoleMenus getById(@PathVariable Integer id) {
        return this.roleMenusService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody RoleMenus roleMenus) {
        this.roleMenusService.insert(roleMenus);
    }

    @PostMapping("/edit")
    public void update(@RequestBody RoleMenus roleMenus) {
        this.roleMenusService.update(roleMenus);
    }
}