package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.blade.manager.system.common.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@RestController
@RequestMapping("/permission/user")
public class UserController extends BaseController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById")
    public User getById() {
         return userService.getById(1);
    }
}
