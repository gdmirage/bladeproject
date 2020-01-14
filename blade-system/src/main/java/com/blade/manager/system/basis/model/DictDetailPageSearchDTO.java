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
 * @since 2019-12-20 15:53:36
 */
public class DictDetailPageSearchDTO extends PageSearchDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    private Long dictId;

    /**
     * 字典标签
     */
    private String label;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
