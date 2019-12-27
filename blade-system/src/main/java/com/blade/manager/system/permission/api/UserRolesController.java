package com.blade.manager.system.permission.api;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.UserRoles;
import com.blade.manager.system.permission.model.UserRolesPageSearchDTO;
import com.blade.manager.system.permission.service.IUserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@RestController("ApiUserRolesController")
@RequestMapping("/api/permission/userRoles")
public class UserRolesController extends BaseController {
    private IUserRolesService userRolesService;

    @Autowired
    public UserRolesController (IUserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    @PostMapping("/page")
    public PageInfo<UserRoles> page(@RequestBody UserRolesPageSearchDTO userRolesPageSearchDTO) {
        return this.userRolesService.page(userRolesPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.userRolesService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public UserRoles getById(@PathVariable Integer id) {
        return this.userRolesService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody UserRoles userRoles) {
        this.userRolesService.insert(userRoles);
    }

    @PostMapping("/edit")
    public void update(@RequestBody UserRoles userRoles) {
        this.userRolesService.update(userRoles);
    }
}