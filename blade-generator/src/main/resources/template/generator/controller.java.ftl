package ${controllerPath};

import java.time.LocalDateTime;
import com.blade.core.controller.BaseController;
import com.blade.core.model.response.ResponseResult;
import com.blade.core.page.PageInfo;
import ${servicePath}.${serviceName};
import ${entityPath}.${entityName};
import ${pageSearchPath}.${pageSearchName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    * ${remark} 前端控制器
    * </p>
 *
 * @author ${author}
 * @since ${createDate}
 */
@RestController
@RequestMapping("/${module}/${tableName}")
public class ${controllerName} extends BaseController {
    private ${serviceName} ${namingService};

    @Autowired
    public ${controllerName} (${serviceName} ${namingService}) {
        this.${namingService} = ${namingService};
    }

    @PostMapping("/page")
    public ResponseResult <PageInfo<${entityName}>> page(@RequestBody ${entityName}PageSearchDTO ${namingEntity}PageSearchDTO) {
        return ResponseResult.ok(200, "请求成功", this.${namingService}.page(${namingEntity}PageSearchDTO));
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Integer id) {
        this.${namingService}.delete(id);
        return ResponseResult.ok();
    }

    @GetMapping("/getById/{id}")
    public ResponseResult<${entityName}> getById(@PathVariable Integer id) {
        return ResponseResult.ok(200, "成功", this.${namingService}.selectByPk(id));
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody ${entityName} ${namingEntity}) {
        ${namingEntity}.setCreateTime(LocalDateTime.now());
        this.${namingService}.insert(${namingEntity});
        return ResponseResult.ok();
    }

    @PostMapping("/edit")
    public ResponseResult update(@RequestBody ${entityName} ${namingEntity}) {
        this.${namingService}.update(${namingEntity});
        return ResponseResult.ok();
    }
}