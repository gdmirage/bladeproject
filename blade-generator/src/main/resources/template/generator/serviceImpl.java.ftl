package ${serviceImplPath};

import ${servicePath}.${serviceName};
import org.springframework.stereotype.Service;
import ${entityPath}.${entityName};
import ${mapperPath}.${mapperName};
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 * ${remark} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${createDate}
 */
@Service("${namingService}")
public class ${serviceImplName} extends BaseServiceImpl<${mapperName}, ${entityName}> implements ${serviceName} {

}