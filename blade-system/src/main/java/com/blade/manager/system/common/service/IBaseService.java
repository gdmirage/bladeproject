package com.blade.manager.system.common.service;

import com.blade.manager.system.common.model.request.PageSearchDTO;
import com.blade.manager.system.common.persistence.entity.BaseEntity;

import java.io.Serializable;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/16 0:02
 */
public interface IBaseService<T extends BaseEntity> {

    /**
     * 新增
     *
     * @param t T extends BaseEntity
     * @return int
     */
    int insert(T t);

    /**
     * 删除
     *
     * @param id id
     * @return int
     */
    int delete(Serializable id);

    /**
     * 更新
     *
     * @param t T extends BaseEntity
     * @return int
     */
    int update(T t);


    /**
     * 根据ID查询
     *
     * @param id Id
     * @return T extends BaseEntity
     */
    T selectByPk(Serializable id);


    void pageTest(PageSearchDTO pageSearchDTO);
}
