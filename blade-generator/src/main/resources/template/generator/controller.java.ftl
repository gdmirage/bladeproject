package ${controllerPath};

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
    public PageInfo<${entityName}> page(@RequestBody ${entityName}PageSearchDTO ${namingEntity}PageSearchDTO) {
        return this.${namingService}.page(${namingEntity}PageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.${namingService}.delete(id);
    }

    @GetMapping("/getById/{id}")
    public ${entityName} getById(@PathVariable Integer id) {
        return this.${namingService}.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody ${entityName} ${namingEntity}) {
        this.${namingService}.insert(${namingEntity});
    }

    @PostMapping("/edit")
    public void update(@RequestBody ${entityName} ${namingEntity}) {
        this.${namingService}.update(${namingEntity});
    }
}