package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.common.BaseController;
import com.blade.manager.system.common.ResponseResult;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.model.menu.MenuListSearchDTO;
import com.blade.manager.system.modules.permission.model.menu.MenuListVO;
import com.blade.manager.system.modules.permission.model.menu.MenuVO;
import com.blade.manager.system.modules.permission.service.IMenuService;
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
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

//    @GetMapping("/getMenuTree")
//    public ResponseResult<List<MenuDTO>> getMenuTree() {
//        return ResponseResult.ok(200, "成功", menuService.getUserMenuByUsername("admin"));
//    }

    @GetMapping("/getMenuTree")
    public List<MenuVO> getMenuTree() {
        return menuService.getUserMenuByUsername("admin");
    }

    @PostMapping("/getMenuList")
    public ResponseResult<List<MenuListVO>> page(@RequestBody MenuListSearchDTO menuListSearchDTO) {
        return ResponseResult.ok(200, "请求成功", menuService.getMenuTree(menuListSearchDTO));
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Integer id) {
        menuService.removeById(id);
        return ResponseResult.ok();
    }

    @GetMapping("/getById")
    public ResponseResult<Menu> getById(Integer id) {
        return ResponseResult.ok(200, "成功", menuService.getById(id));
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Menu menu) {
        menu.setCreateTime(LocalDateTime.now());
        menuService.save(menu);
        return ResponseResult.ok();
    }

    @PostMapping("/edit")
    public ResponseResult update(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return ResponseResult.ok();
    }
}
