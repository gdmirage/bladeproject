package com.blade.core.page;

/**
 * 最主要的是用 {@link ThreadLocal} 管理 {@link Page}
 * 这样，在拦截器的时候，能够方便获取 {@link Page}
 *
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
