package com.blade.manager.system.common.service;

import com.blade.manager.system.common.persistence.CrudDAO;
import com.blade.manager.system.common.persistence.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/15 20:00
 */
public class CrudServiceImpl<D extends CrudDAO<T>, T extends BaseEntity>{

    @Autowired
    protected D Dao;

    /**
     * 新增
     * @param t T extends BaseEntity
     * @return int
     */
    public int insert(T t) {
        return Dao.insert(t);
    }

    /**
     * 删除
     * @param id id
     * @return int
     */
    public int delete(long id) {
        return Dao.deleteByPK(id);
    }

    /**
     * 更新
     * @param t T extends BaseEntity
     * @return int
     */
    public int update(T t) {
        return Dao.update(t);
    }

    /**
     * 根据ID查询
     * @param id Id
     * @return T extends BaseEntity
     */
    public T selectByPK(long id) {
        return Dao.selectByPK(id);
    }
}