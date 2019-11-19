package com.blade.generator.core;

import java.util.Objects;

/**
 * @author blade
 * 2019/10/24 10:15
 */
public enum DbTypeMappingEnum {

    /**
     * 字节类型 BIT
     */
    BIT("BIT", "BIT", "Integer", ""),

    /**
     * 数值类型 TINYINT
     */
    TINYINT("TINYINT", "TINYINT", "Integer", ""),

    /**
     * 数值类型 INT
     */
    INT("INT", "INTEGER", "Integer", ""),

    /**
     * 数值类型 BIGINT
     */
    BIGINT("BIGINT", "BIGINT", "Long", ""),

    /**
     * 字符类型 CHAR
     */
    CHAR("CHAR", "CHAR", "String", ""),

    /**
     * 字符类型 VARCHAR
     */
    VARCHAR("VARCHAR", "VARCHAR", "String", ""),

    /**
     * 时间类型 DATE
     */
    DATE("DATE", "DATE", "LocalDate", "java.time.LocalDate"),

    /**
     * 时间类型 DATA_TIME
     */
    DATA_TIME("DATETIME", "TIMESTAMP", "LocalDateTime", "java.time.LocalDateTime"),

    /**
     * 时间类型 TIMESTAMP
     */
    TIMESTAMP("TIMESTAMP", "TIMESTAMP", "LocalDateTime", "java.time.LocalDateTime"),

    /**
     * 浮点类型 DOUBLE
     */
    DOUBLE("DOUBLE", "DOUBLE", "Double", ""),

    /**
     * 浮点类型 DECIMAL
     */
    DECIMAL("DECIMAL", "DECIMAL", "BigDecimal", "java.math.BigDecimal"),

    /**
     * 浮点类型 FLOAT
     */
    FLOAT("FLOAT", "FLOAT", "Float", ""),

    /**
     * 文本类型 TINYTEXT
     */
    TINYTEXT("TINYTEXT", "LONGVARCHAR", "String", ""),

    /**
     * 文本类型 TINYBLOB
     */
    TINYBLOB("TINYBLOB", "TINYBLOB", "byte[]", ""),

    /**
     * 文本类型 TEXT
     */
    TEXT("TEXT", "LONGVARCHAR", "String", ""),

    /**
     * 文本类型 BLOB
     */
    BLOB("BLOB", "BLOB", "byte[]", ""),

    /**
     * 文本类型 LONGBLOB
     */
    LONGBLOB("LONGBLOB", "LONGBLOB", "byte[]", ""),

    /**
     * 文本类型 LONGTEXT
     */
    LONGTEXT("LONGTEXT", "LONGVARCHAR", "String", "");

    private String dbType;
    private String jdbcType;
    private String javaType;
    private String importClass;

    DbTypeMappingEnum(String dbType, String jdbcType, String javaType, String importClass) {
        this.dbType = dbType;
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

    public String getDbType() {
        return dbType;
    }

    public static String dbType2JavaType(String dbType) {
        DbTypeMappingEnum[] jdbcTypeToJavaTypes = DbTypeMappingEnum.values();
        for (DbTypeMappingEnum jdbcTypeToJavaType : jdbcTypeToJavaTypes) {
            if (Objects.equals(jdbcTypeToJavaType.getDbType(), dbType)) {
                return jdbcTypeToJavaType.getJavaType();
            }
        }

        return null;
    }

    public static String getImportClass(String jdbcType) {
        DbTypeMappingEnum[] jdbcTypeToJavaTypes = DbTypeMappingEnum.values();
        for (DbTypeMappingEnum jdbcTypeToJavaType : jdbcTypeToJavaTypes) {
            if (Objects.equals(jdbcTypeToJavaType.getJdbcType(), jdbcType)) {
                return jdbcTypeToJavaType.getImportClass();
            }
        }

        return null;
    }

    /**
     * 数据库字段类型转成 jdbc 类型， 主要是为了生成mapper.xml文件
     * @param dbType 数据库字段类型
     * @return String jdbcType
     */
    public static String dbType2JdbcType(String dbType) {
        DbTypeMappingEnum[] jdbcTypeToJavaTypes = DbTypeMappingEnum.values();
        for (DbTypeMappingEnum jdbcTypeToJavaType : jdbcTypeToJavaTypes) {
            if (Objects.equals(jdbcTypeToJavaType.getDbType(), dbType)) {
                return jdbcTypeToJavaType.getJdbcType();
            }
        }

        return null;
    }
}
