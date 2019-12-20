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
     * ID
     */
    private Long id;


    /**
     * 创建人
     */
    private String creator;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 修改人
     */
    private String modifier;


    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


    /**
     * 是否删除。0-未删除。1-已删除
     */
    private Integer isDelete;


    /**
     * 字典名称
     */
    private String dictName;


    /**
     * 备注
     */
    private String remark;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public LocalDateTime getModifyTime() {
        return this.modifyTime;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() {
        return this.isDelete;
    }

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
