package com.blade.manager.system.common.persistence;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/15 14:23
 */
public interface CrudDAO<T> {

    /**
     * 新增
     * @param t T
     * @return int
     */
    int insert(T t);

    /**
     * 逻辑删除
     * @param id
     * @return int
     */
    int deleteByPK(long id);

    /**
     * 更新
     * @param t T
     * @return int
     */
    int update(T t);

    /**
     * 根据主键查找
     * @param id id
     * @return T
     */
    T selectByPK(long id);
}
