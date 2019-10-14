package com.blade.manager.system.common.model.request;

import java.io.Serializable;

/**
 * @author blade
 * 2019/10/14 16:00
 */
public class PageSearchDTO implements Serializable{
    private static final long serialVersionUID = 3526051800407142050L;

    private int pageNum = 1;

    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
