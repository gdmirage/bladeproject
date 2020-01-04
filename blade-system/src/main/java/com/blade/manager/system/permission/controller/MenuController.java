package com.blade.manager.system.permission.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.service.IMenuService;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.model.menu.MenuPageSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/permission/menu")
public class MenuController extends BaseController {
    private IMenuService menuService;

    @Autowired
    public MenuController (IMenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/page")
    public PageInfo<Menu> page(@RequestBody MenuPageSearchDTO menuPageSearchDTO) {
        return this.menuService.page(menuPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.menuService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Menu getById(@PathVariable Integer id) {
        return this.menuService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Menu menu) {
        this.menuService.insert(menu);
    }

    @PostMapping("/edit")
    public void update(@RequestBody Menu menu) {
        this.menuService.update(menu);
    }
}