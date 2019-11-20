package com.blade.core.model.request;

import com.blade.core.model.base.JsonAble;

/**
 * @author blade
 * 2019/10/14 16:00
 */
public class PageSearchDTO extends JsonAble{
    private static final long serialVersionUID = 3526051800407142050L;

    private int pageNumber = 1;

    private int pageSize = 10;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
