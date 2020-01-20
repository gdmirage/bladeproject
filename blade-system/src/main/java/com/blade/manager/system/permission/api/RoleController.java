package com.blade.manager.system.permission.api;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.model.role.RoleInsertOrUpdateVO;
import com.blade.manager.system.permission.model.role.RoleListVO;
import com.blade.manager.system.permission.model.role.RolePageSearchDTO;
import com.blade.manager.system.permission.service.IRoleService;
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
@RestController("ApiRoleController")
@RequestMapping("/api/permission/role")
public class RoleController extends BaseController {
    private static final long serialVersionUID = -7467854986025334626L;
    private IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/page")
    public PageInfo<RoleListVO> page(@RequestBody RolePageSearchDTO rolePageSearchDTO) {
        return this.roleService.selectPage(rolePageSearchDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        this.roleService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Role getById(@PathVariable Integer id) {
        return this.roleService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Role role) {
        this.roleService.insert(role);
    }

    @PutMapping("/edit")
    public void update(@RequestBody RoleInsertOrUpdateVO role) {
        this.roleService.update(role);
    }
}