<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPath}.${mapperName}">
    <!-- ===================================generated code============================================ -->
    <resultMap id="BaseResultMap" type="${entityPath}.${entityName}">
    <#list columns as field>
        <result column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.propertyName}" />
    </#list>
    </resultMap>

    <sql id="BaseColumn">
    <#list columns as field>
        ${field.columnName}<#if field_has_next>,</#if>
    </#list>
    </sql>

    <select id="selectByPk" resultMap="BaseResultMap">
        SELECT <include refid="BaseColumn" /> FROM ${tableName}
        WHERE ${keyColumn} = <#noparse>#</#noparse>{${keyColumn}}
    </select>

    <delete id="deleteByPk">
        DELETE FROM ${tableName} WHERE ${keyColumn} = <#noparse>#</#noparse>{${keyColumn}}
    </delete>

    <insert id="insert" parameterType="${entityPath}.${entityName}">
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list columns as field>
            <if test="${field.propertyName} != null">
                ${field.columnName},
            </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list columns as field>
            <if test="${field.propertyName} != null">
                <#noparse>#</#noparse>{${field.propertyName}},
            </if>
            </#list>
        </trim>
    </insert>

    <update id="update" parameterType="${entityPath}.${entityName}">
        UPDATE ${tableName}
        <set>
            <#list columns as field>
            <#if (field.propertyName != keyColumn)>
            <if test="${field.propertyName} != null">
                ${field.columnName} = <#noparse>#</#noparse>{${field.propertyName}},
            </if>
            </#if>
            </#list>
        </set>
        where ${keyColumn} = <#noparse>#</#noparse>{${keyColumn}}
    </update>

    <!-- ===================================generated code============================================ -->
</mapper>
