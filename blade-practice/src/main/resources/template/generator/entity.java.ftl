package ${packagePath};

import java.io.Serializable;
<#list importClasses as importClass>
import ${importClass};
</#list>

/**
 * <p>
 * ${description}
 * </p>
 *
 * @author ${author}
 * @since ${createDate}
 */
public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;

<#-- ----------  START 字段循环遍历  ---------->
<#list properties as field>

    /**
     * ${field.description}
     */
    private ${field.javaType} ${field.propertyName};

</#list>
<#------------  END 字段循环遍历  ---------->

<#------------  START 字段 getter setter 循环遍历  ---------->
<#list properties as field>
    public void set${field.propertyName}(${field.javaType} ${field.propertyName}) {
        this.${field.propertyName} = ${field.propertyName};
    }

    public ${field.javaType} get${field.propertyName}() {
        return this.${field.propertyName};
    }

</#list>
<#------------  END 字段 getter setter 循环遍历  ---------->
}
