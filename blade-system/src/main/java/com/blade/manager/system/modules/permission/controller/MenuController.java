package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.common.BaseController;
import com.blade.manager.system.common.ResponseResult;
import com.blade.manager.system.modules.permission.model.menu.MenuDTO;
import com.blade.manager.system.modules.permission.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/getMenuTree")
    public ResponseResult<List<MenuDTO>> getMenuTree() {
        return ResponseResult.ok(200, "成功", menuService.getUserMenuByUsername("admin"));
    }
}
