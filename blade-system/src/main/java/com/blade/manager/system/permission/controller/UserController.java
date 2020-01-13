package com.blade.manager.system.permission.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.model.UserPageSearchDTO;
import com.blade.manager.system.permission.service.IUserService;
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
@RequestMapping("/permission/user")
public class UserController extends BaseController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/page")
    public PageInfo<User> page(@RequestBody UserPageSearchDTO userPageSearchDTO) {
        return this.userService.page(userPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.userService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable Integer id) {
        return this.userService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        this.userService.insert(user);
    }

    @PostMapping("/edit")
    public void update(@RequestBody User user) {
        this.userService.update(user);
    }
}