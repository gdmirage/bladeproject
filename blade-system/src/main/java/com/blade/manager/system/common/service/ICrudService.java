package com.blade.manager.system.common.service;

import com.blade.manager.system.common.persistence.CrudDAO;
import com.blade.manager.system.common.persistence.entity.BaseEntity;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/16 0:02
 */
public interface ICrudService<D extends CrudDAO<T>, T extends BaseEntity> {

    /**
     * 新增
     * @param t T extends BaseEntity
     * @return int
     */
    int insert(T t);

    /**
     * 删除
     * @param id id
     * @return int
     */
    int delete(long id);

    /**
     * 更新
     * @param t T extends BaseEntity
     * @return int
     */
    int update(T t);


    /**
     * 根据ID查询
     * @param id Id
     * @return T extends BaseEntity
     */
    T selectByPK(long id);


}
