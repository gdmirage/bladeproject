package com.blade.core.page;

/**
 * @author blade
 * 2019/11/25 16:36
 */
public class PageMethod {
    private static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<>();

    public static void setLocalPage(Page page) {
        LOCAL_PAGE.set(page);
    }

    public static <T> Page<T> getLocalPage() {
        return LOCAL_PAGE.get();
    }

    public static void clearPage() {
        LOCAL_PAGE.remove();
    }
}
