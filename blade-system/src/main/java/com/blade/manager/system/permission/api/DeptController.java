package com.blade.manager.system.permission.api;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.model.dept.DeptListSearchDTO;
import com.blade.manager.system.permission.model.dept.DeptListTreeVO;
import com.blade.manager.system.permission.model.dept.DeptPageSearchDTO;
import com.blade.manager.system.permission.model.dept.DeptTreeVO;
import com.blade.manager.system.permission.service.IDeptService;
import io.swagger.annotations.Api;
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

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Api(value = "部门API", tags = "部门API")
@RestController("ApiDeptController")
@RequestMapping("/api/permission/dept")
public class DeptController extends BaseController {
    private static final long serialVersionUID = 3604322655867958936L;
    private IDeptService deptService;

    @Autowired
    public DeptController(IDeptService deptService) {
        this.deptService = deptService;
    }

    @PostMapping("/page")
    public PageInfo<Dept> page(@RequestBody DeptPageSearchDTO deptPageSearchDTO) {
        return this.deptService.page(deptPageSearchDTO);
    }

    @PostMapping("/deptList")
    public PageInfo<DeptListTreeVO> getDeptListTree(@RequestBody DeptListSearchDTO searchDTO) {
        return this.deptService.getDeptListTree(searchDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        this.deptService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Dept getById(@PathVariable Integer id) {
        return this.deptService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Dept dept) {
        this.deptService.insert(dept);
    }

    @PutMapping("/edit")
    public void update(@RequestBody Dept dept) {
        this.deptService.update(dept);
    }

    @GetMapping("/getDeptTree")
    public List<DeptTreeVO> getDeptTree() {
        return this.deptService.getDeptTree();
    }
}