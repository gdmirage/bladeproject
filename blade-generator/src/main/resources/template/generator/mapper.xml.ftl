<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPath}.${mapperName}">
    <!-- ===================================generated code============================================ -->
    <resultMap id="BaseResultMap" type="${entityPath}.${entityName}">
    <#list columns as field>
        <result column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.propertyName}" />
    </#list>
    </resultMap>

    <sql id="table_name">${tableName}</sql>
    <sql id="BaseColumn">
    <#list columns as field>
        ${field.columnName}<#if field_has_next>,</#if>
    </#list>
    </sql>

    <select id="selectByPk" resultMap="BaseResultMap">
        SELECT <include refid="BaseColumn" />
        FROM <include refid="table_name" />
        WHERE ${keyColumn} = <#noparse>#</#noparse>{${keyColumn}}
    </select>

    <delete id="deleteByPk">
        DELETE FROM <include refid="table_name" /> WHERE ${keyColumn} = <#noparse>#</#noparse>{${keyColumn}}
    </delete>

    <insert id="insert" parameterType="${entityPath}.${entityName}">
        INSERT INTO <include refid="table_name" />
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
        UPDATE <include refid="table_name" />
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

    <select id="selectPageList" resultMap="BaseResultMap" parameterType="com.blade.core.model.request.PageSearchDTO">
        SELECT
        <include refid="BaseColumn"/>
        FROM
        <include refid="table_name" />
        <where>
            1 = 1
            <#list columns as field>
            <#if (field.propertyName != keyColumn)>
            <if test="searchDTO.${field.propertyName} != null">
                AND ${field.columnName} = <#noparse>#</#noparse>{searchDTO.${field.propertyName}},
            </if>
            </#if>
            </#list>
        </where>
    </select>

    <!-- ===================================generated code============================================ -->
</mapper>
