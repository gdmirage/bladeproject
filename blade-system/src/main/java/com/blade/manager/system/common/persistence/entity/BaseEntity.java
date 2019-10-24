package com.blade.manager.system.common.persistence.entity;

import java.util.Date;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/15 21:19
 */
public class BaseEntity {

    public Long id;

    public String createMan;

    public Date createDate;

    public String updateMan;

    public Date updateDate;

    public int isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    public Date getCreateDate() {
        return new Date(createDate.getTime());
    }

    public void setCreateDate(Date createDate) {
        this.createDate = new Date(createDate.getTime());
    }

    public String getUpdateMan() {
        return updateMan;
    }

    public void setUpdateMan(String updateMan) {
        this.updateMan = updateMan == null ? null : updateMan.trim();
    }

    public Date getUpdateDate() {
        return new Date(updateDate.getTime());
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = new Date(updateDate.getTime());
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
