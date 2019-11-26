package ${pageSearchPath};

import java.io.Serializable;
package com.blade.core.model.request.PageSearchDTO;
<#list importClasses as importClass>
import ${importClass};
</#list>

/**
 * <p>
 * ${remark} 分页查询条件
 * </p>
 *
 * @author ${author}
 * @since ${createDate}
 */
public class ${pageSearchName} extends PageSearchDTO {
    private static final long serialVersionUID = 1L;

<#-- ----------  START 字段循环遍历  ---------->
<#list columns as field>

    /**
     * ${field.remark}
     */
    private ${field.javaType} ${field.propertyName};

</#list>
<#------------  END 字段循环遍历  ---------->

<#------------  START 字段 getter setter 循环遍历  ---------->
<#list columns as field>
    public void set${field.methodName}(${field.javaType} ${field.propertyName}) {
        this.${field.propertyName} = ${field.propertyName};
    }

    public ${field.javaType} get${field.methodName}() {
        return this.${field.propertyName};
    }

</#list>
<#------------  END 字段 getter setter 循环遍历  ---------->
}
