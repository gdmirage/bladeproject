package com.blade.core.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本分页类
 * 继承 {@link ArrayList} 是为了可以更方便从query中返回结果
 *
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

    /**
     * 查询分页详情信息
     *
     * @param select {@link ISelect}
     * @param <T>    泛型
     * @return {@link PageInfo<T>} 返回分页的详细信息
     */
    public <T> PageInfo<T> doSelectPageInfo(ISelect select) {
        select.doSelect();
        return toPageInfo(this);
    }

    public <T> PageInfo<T> toPageInfo(Page page) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNumber(page.getPageNumber());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setRecordList(page.getRecordList());
        pageInfo.setTotalCount(page.getTotalCount());
        return pageInfo;
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
