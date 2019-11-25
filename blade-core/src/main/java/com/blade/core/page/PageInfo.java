package com.blade.core.page;

import java.util.List;

/**
 * 分页的详细信息
 * 此类可以添加一些个性的参数，返回给前端就可以了
 *
 * @author blade
 * 2019/11/25 16:15
 */
public class PageInfo<T> {
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
