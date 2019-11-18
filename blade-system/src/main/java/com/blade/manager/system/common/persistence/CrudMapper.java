package com.blade.manager.system.common.persistence;

import java.io.Serializable;

/**
 * 基本的CRUD操作
 *
 * @author Blade
 * @date 2019/2/15 14:23
 */
public interface CrudMapper<T> {

    /**
     * 新增
     *
     * @param t T
     * @return int
     */
    int insert(T t);

    /**
     * 物理删除
     *
     * @param pk 主键
     * @return int
     */
    int deleteByPk(Serializable pk);

    /**
     * 更新
     *
     * @param t T
     * @return int
     */
    int update(T t);

    /**
     * 根据主键查找
     *
     * @param pk 主键
     * @return T
     */
    T selectByPk(Serializable pk);
}
