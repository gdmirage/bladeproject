package com.blade.manager.system.common.service;

import com.blade.manager.system.common.persistence.BaseMapper;
import com.blade.manager.system.common.persistence.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/15 20:00
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> implements IBaseService<T> {

    @Autowired
    protected M baseMapper;

    /**
     * 新增
     *
     * @param t T extends BaseEntity
     * @return int
     */
    @Override
    public int insert(T t) {
        return this.baseMapper.insert(t);
    }

    /**
     * 删除
     *
     * @param pk primary key
     * @return int
     */
    @Override
    public int delete(Serializable pk) {
        return this.baseMapper.deleteByPk(pk);
    }

    /**
     * 更新
     *
     * @param t T extends BaseEntity
     * @return int
     */
    @Override
    public int update(T t) {
        return this.baseMapper.update(t);
    }

    /**
     * 根据ID查询
     *
     * @param pk primary key
     * @return T extends BaseEntity
     */
    @Override
    public T selectByPk(Serializable pk) {
        return this.baseMapper.selectByPk(pk);
    }
}
