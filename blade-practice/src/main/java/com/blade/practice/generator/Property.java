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

    /**
     * 方法名  主要是针对get set 方法。第一个单词也要大写
     */
    private String methodName;


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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
