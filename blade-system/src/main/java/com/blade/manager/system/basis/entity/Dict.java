package com.blade.manager.system.basis.entity;

import com.blade.core.persistence.entity.BaseEntity;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 14:34:01
 */
public class Dict extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 备注
     */
    private String remark;

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictName() {
        return this.dictName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

}
