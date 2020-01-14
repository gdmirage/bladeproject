package com.blade.manager.system.basis.model;

import java.io.Serializable;
import com.blade.core.model.request.PageSearchDTO;
import java.time.LocalDateTime;

/**
 * <p>
 *  分页查询条件
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 14:34:01
 */
public class DictPageSearchDTO extends PageSearchDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String dictName;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
