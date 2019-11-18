package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.common.BaseController;
import com.blade.manager.system.common.ResponseResult;
import com.blade.manager.system.modules.permission.entity.Permission;
import com.blade.manager.system.modules.permission.model.permission.PermissionListVO;
import com.blade.manager.system.modules.permission.model.permission.PermissionSearchDTO;
import com.blade.manager.system.modules.permission.model.permission.PermissionTreeVO;
import com.blade.manager.system.modules.permission.service.IPermissionService;
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
 * @since 2019-09-17
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    private IPermissionService permissionService;

    @Autowired
    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/permissionTree")
    public ResponseResult<List<PermissionTreeVO>> permissionTree() {
        return ResponseResult.ok(200, "请求成功", permissionService.getPermissionTree());
    }

    @PostMapping("/getPermissionList")
    public ResponseResult<List<PermissionListVO>> page(@RequestBody PermissionSearchDTO permissionSearchDTO) {
        return ResponseResult.ok(200, "请求成功", permissionService.getPermissionList(permissionSearchDTO));
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Integer id) {
        permissionService.delete(id);
        return ResponseResult.ok();
    }

    @GetMapping("/getById")
    public ResponseResult<Permission> getById(Integer id) {
        return ResponseResult.ok(200, "成功", permissionService.selectByPk(id));
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Permission permission) {
        permission.setCreateTime(LocalDateTime.now());
        permissionService.insert(permission);
        return ResponseResult.ok();
    }

    @PostMapping("/edit")
    public ResponseResult update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return ResponseResult.ok();
    }
}
