package com.blade.manager.system.modules.permission.entity;

import com.blade.core.persistence.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author blade
 * @since 2019-10-06
 */
public class DictDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private String sort;

    /**
     * 字典id
     */
    private Long dictId;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }
}
