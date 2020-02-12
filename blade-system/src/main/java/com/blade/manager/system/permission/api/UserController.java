package com.blade.manager.system.permission.api;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.model.user.UserInsertOrUpdateDTO;
import com.blade.manager.system.permission.model.user.UserListVO;
import com.blade.manager.system.permission.model.user.UserPageSearchDTO;
import com.blade.manager.system.permission.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@RestController("ApiUserController")
@RequestMapping("/api/permission/user")
public class UserController extends BaseController {
    private static final long serialVersionUID = 8390166634651333841L;
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/page")
    public PageInfo<UserListVO> page(@RequestBody UserPageSearchDTO userPageSearchDTO) {
        return this.userService.page(userPageSearchDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        this.userService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable Long id) {
        return this.userService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody UserInsertOrUpdateDTO user) {
        this.userService.insert(user);
    }

    @PutMapping("/edit")
    public void update(@RequestBody UserInsertOrUpdateDTO user) {
        this.userService.update(user);
    }
}