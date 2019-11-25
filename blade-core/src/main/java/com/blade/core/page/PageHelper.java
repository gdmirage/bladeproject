package com.blade.core.page;

/**
 * 分页Helper 存放一些常用的方法
 *
 * @author blade
 * 2019/11/25 17:06
 */
public class PageHelper extends PageMethod {

    /**
     * 开始分页
     *
     * @param pageNumber 页码
     * @param pageSize   每页条数
     * @param <E>        泛型
     * @return {@link Page<E>}
     */
    public static <E> Page<E> startPage(int pageNumber, int pageSize) {
        Page<E> page = new Page<>(pageNumber, pageSize);
        setLocalPage(page);
        return page;
    }
}
