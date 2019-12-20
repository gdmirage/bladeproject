package ${entityPath};

import com.blade.core.persistence.entity.BaseEntity;
<#list importClasses as importClass>
import ${importClass};
</#list>

/**
 * <p>
 * ${remark}
 * </p>
 *
 * @author ${author}
 * @since ${createDate}
 */
public class ${entityName} extends BaseEntity {
    private static final long serialVersionUID = 1L;

<#-- ----------  START 字段循环遍历  ---------->
<#list columns as field>
    <#if !entityIgnoreColumn?seq_contains(field.columnName) >
    /**
     * ${field.remark}
     */
    private ${field.javaType} ${field.propertyName};

    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

<#------------  START 字段 getter setter 循环遍历  ---------->
<#list columns as field>
    <#if !entityIgnoreColumn?seq_contains(field.columnName) >
    public void set${field.methodName}(${field.javaType} ${field.propertyName}) {
        this.${field.propertyName} = ${field.propertyName};
    }

    public ${field.javaType} get${field.methodName}() {
        return this.${field.propertyName};
    }

    </#if>
</#list>
<#------------  END 字段 getter setter 循环遍历  ---------->
}
