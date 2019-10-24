package com.blade.practice.generator;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author blade
 * 2019/10/24 10:15
 */
public enum JdbcTypeToJavaType {

    /**
     * 数值类型 TINYINT
     */
    TINYINT("TINYINT", "Integer", ""),

    /**
     * 数值类型 INT
     */
    INT("INT", "Integer", ""),

    /**
     * 数值类型 BIGINT
     */
    BIGINT("BIGINT", "Long", ""),

    /**
     * 字符类型 CHAR
     */
    CHAR("CHAR", "String", ""),

    /**
     * 字符类型 VARCHAR
     */
    VARCHAR("VARCHAR", "String", ""),

    /**
     * 时间类型 DATE
     */
    DATE("DATE", "LocalDate", "java.time.LocalDate"),

    /**
     * 时间类型 DATA_TIME
     */
    DATA_TIME("DATETIME", "LocalDateTime", "java.time.LocalDateTime"),

    /**
     * 时间类型 TIMESTAMP
     */
    TIMESTAMP("TIMESTAMP", "LocalDateTime", "java.time.LocalDateTime"),

    /**
     * 浮点类型 DOUBLE
     */
    DOUBLE("DOUBLE", "Double", ""),

    /**
     * 浮点类型 DECIMAL
     */
    DECIMAL("DECIMAL", "BigDecimal", "java.math.BigDecimal"),

    /**
     * 浮点类型 FLOAT
     */
    FLOAT("FLOAT", "Float", ""),

    /**
     * 文本类型 TINYTEXT
     */
    TINYTEXT("TINYTEXT", "String", ""),

    /**
     * 文本类型 TINYBLOB
     */
    TINYBLOB("TINYBLOB", "String", ""),

    /**
     * 文本类型 TEXT
     */
    TEXT("TEXT", "String", ""),

    /**
     * 文本类型 BLOB
     */
    BLOB("BLOB", "String", ""),

    /**
     * 文本类型 LONGBLOB
     */
    LONGBLOB("LONGBLOB", "String", ""),

    /**
     * 文本类型 LONGTEXT
     */
    LONGTEXT("LONGTEXT", "String", "");

    private String jdbcType;
    private String javaType;
    private String importClass;

    JdbcTypeToJavaType(String jdbcType, String javaType, String importClass) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.importClass = importClass;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getImportClass() {
        return importClass;
    }

    @Nullable
    public static String jdbcType2JavaType(String jdbcType) {
        JdbcTypeToJavaType[] jdbcTypeToJavaTypes = JdbcTypeToJavaType.values();
        for (JdbcTypeToJavaType jdbcTypeToJavaType : jdbcTypeToJavaTypes) {
            if (Objects.equals(jdbcTypeToJavaType.getJdbcType(), jdbcType)) {
                return jdbcTypeToJavaType.getJavaType();
            }
        }

        return null;
    }

    @Nullable
    public static String getImportClass(String jdbcType) {
        JdbcTypeToJavaType[] jdbcTypeToJavaTypes = JdbcTypeToJavaType.values();
        for (JdbcTypeToJavaType jdbcTypeToJavaType : jdbcTypeToJavaTypes) {
            if (Objects.equals(jdbcTypeToJavaType.getJdbcType(), jdbcType)) {
                return jdbcTypeToJavaType.getImportClass();
            }
        }

        return null;
    }
}
