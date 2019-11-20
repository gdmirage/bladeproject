package com.blade.manager.system.modules.permission.controller;


import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.model.user.UserInsertOrUpdateDTO;
import com.blade.manager.system.modules.permission.model.user.UserListVO;
import com.blade.manager.system.modules.permission.model.user.UserPageSearchDTO;
import com.blade.manager.system.modules.permission.service.IUserService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/user")
public class UserController extends BaseController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<UserListVO>> page(@RequestBody UserPageSearchDTO userPageSearchDTO) {
        return ResponseResult.ok(200, "请求成功", this.userService.page(userPageSearchDTO));
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Long id) {
        this.userService.delete(id);
        return ResponseResult.ok();
    }

    @GetMapping("/getById")
    public ResponseResult<User> getById(Integer id) {
        return ResponseResult.ok(200, "成功", this.userService.selectByPk(id));
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserInsertOrUpdateDTO userInsertOrUpdateDTO) {
        userInsertOrUpdateDTO.setCreateTime(LocalDateTime.now());
        this.userService.save(userInsertOrUpdateDTO);
        return ResponseResult.ok();
    }

    @PostMapping("/edit")
    public ResponseResult update(@RequestBody UserInsertOrUpdateDTO userInsertOrUpdateDTO) {
        this.userService.update(userInsertOrUpdateDTO);
        return ResponseResult.ok();
    }
}
