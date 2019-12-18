package com.blade.manager.system.modules.permission.controller;


import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.modules.permission.model.role.RoleInsertOrUpdateVO;
import com.blade.manager.system.modules.permission.model.role.RoleListVO;
import com.blade.manager.system.modules.permission.model.role.RoleMenuUpdateVO;
import com.blade.manager.system.modules.permission.model.role.RolePageSearchDTO;
import com.blade.manager.system.modules.permission.model.role.RolePermissionUpdateVO;
import com.blade.manager.system.modules.permission.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    private IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<RoleListVO>> page(@RequestBody RolePageSearchDTO rolePageSearchDTO) {
        return ResponseResult.ok(200, "请求成功", roleService.page(rolePageSearchDTO));
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Long id) {
        roleService.delete(id);
        return ResponseResult.ok();
    }

    @GetMapping("/getById")
    public ResponseResult<RoleListVO> getById(Long id) {
        return ResponseResult.ok(200, "成功", roleService.getRoleById(id));
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody RoleInsertOrUpdateVO role) {
        role.setCreateTime(LocalDateTime.now());
        roleService.add(role);
        return ResponseResult.ok();
    }

    @PostMapping("/edit")
    public ResponseResult update(@RequestBody RoleInsertOrUpdateVO role) {
        roleService.update(role);
        return ResponseResult.ok();
    }

    @PostMapping("/permissions")
    public ResponseResult updatePermissions(@RequestBody RolePermissionUpdateVO rolePermissionUpdateVO) {
        this.roleService.updatePermissions(rolePermissionUpdateVO);
        return ResponseResult.ok();
    }

    @PostMapping("/menus")
    public ResponseResult updateMenus(@RequestBody RoleMenuUpdateVO roleMenuUpdateVO) {
        this.roleService.updateMenus(roleMenuUpdateVO);
        return ResponseResult.ok();
    }

    @GetMapping("/level")
    public ResponseResult getCurrentUserRoleLevel() {
        return ResponseResult.ok(1);
    }
}
