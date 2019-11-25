package com.blade.core.page;

/**
 * @author blade
 * 2019/11/25 17:06
 */
public class PageHelper extends PageMethod {

    public static  <E> Page<E> startPage(int pageNumber, int pageSize) {
        Page<E> page = new Page<>(pageNumber, pageSize);
        setLocalPage(page);
        return page;
    }
}
