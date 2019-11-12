package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.common.BaseController;
import com.blade.manager.system.common.ResponseResult;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.model.job.JobPageSearchDTO;
import com.blade.manager.system.modules.permission.model.menu.MenuVO;
import com.blade.manager.system.modules.permission.service.IMenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/page")
    public ResponseResult<PageInfo<Menu>> page(@RequestBody JobPageSearchDTO jobPageSearchDTO) {
//        return ResponseResult.ok(200, "请求成功", menuService.page(jobPageSearchDTO));
        return ResponseResult.ok(200, "请求成功", null);
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
