package com.blade.core.service.impl;

import com.blade.core.model.Page;
import com.blade.core.model.request.PageSearchDTO;
import com.blade.core.persistence.entity.BaseEntity;
import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.core.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/15 20:00
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity>
        implements IBaseService<T> {

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

    @Override
    public Page<T> page(PageSearchDTO pageSearchDTO) {
        Page<T> page = new Page<>();
        page.setPageNumber(pageSearchDTO.getPageNumber());
        page.setPageSize(pageSearchDTO.getPageSize());
        page.setRecordList(this.baseMapper.selectPageList(pageSearchDTO));
        page.setTotalCount(this.baseMapper.selectPageCount(pageSearchDTO));

        return page;
    }
}
