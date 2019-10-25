<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packagePath}.mapper.${className}Mapper">
    <resultMap id="baseResultMap" type="${packagePath}.entity.${className}">
    <#list columns as field>
        <result column="${field.columnName}" jdbcType="${field.columnJdbcType}" property="${field.propertyName}" />
    </#list>
    </resultMap>

    <sql id="baseColumn">
    <#list columns as field>
        ${field.columnName}<#if field_has_next>,</#if>
    </#list>
    </sql>

    <select id="selectById" resultMap="baseResultMap">
        SELECT <include refid="baseColumn" /> FROM ${tableName}
        WHERE id = <#noparse>#</#noparse>{id}
    </select>
</mapper>
