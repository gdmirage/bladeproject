package com.blade.core.model;

import com.blade.core.model.base.JsonAble;

import java.util.ArrayList;
import java.util.List;

/**
 * @author blade
 * 2019/11/20 16:26
 */
public class Page<T> extends ArrayList<T> {
    private static final long serialVersionUID = -2451704274418539172L;

    /**
     * 当前页
     */
    private int pageNumber;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 数据列表
     */
    private List<T> recordList;

    /**
     * 总条数
     */
    private int totalCount;

    public Page(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

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

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
