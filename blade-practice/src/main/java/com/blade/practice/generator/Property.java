package com.blade.practice.generator;

/**
 * @author blade
 * 2019/10/24 11:06
 */
public class Property {

    /**
     * 字段名称
     */
    private String propertyName;

    /**
     * java 类型
     */
    private String javaType;

    /**
     * 描述
     */
    private String description;


    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
