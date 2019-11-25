package com.blade.core.page;

/**
 * 接口
 * 实现由用户调用分页插件的时候编写
 *
 * @author blade
 * 2019/11/25 16:16
 */
public interface ISelect {

    /**
     * 执行查询语句
     */
    void doSelect();
}
