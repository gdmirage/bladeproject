package com.blade.core.service;

import com.blade.core.model.request.PageSearchDTO;
import com.blade.core.page.PageInfo;
import com.blade.core.persistence.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

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

    /**
     * 逻辑删除
     *
     * @param id 主键
     * @return delete num
     */
    int logicallyDeleteByPk(Serializable id);

    /**
     * 批量插入
     *
     * @param list list
     * @return insert num
     */
    int insertBatch(List<T> list);

    /**
     * 批量更新
     *
     * @param list list
     * @return update num
     */
    int updateBatch(List<T> list);

    /**
     * 分页查询
     *
     * @param pageSearchDTO {@link PageSearchDTO}
     * @return {@link PageInfo<T>}
     */
    PageInfo<T> page(PageSearchDTO pageSearchDTO);
}
