package com.blade.core.service.impl;

import com.blade.core.constant.Constants;
import com.blade.core.model.base.LoggingSupport;
import com.blade.core.model.request.PageSearchDTO;
import com.blade.core.page.PageHelper;
import com.blade.core.page.PageInfo;
import com.blade.core.persistence.entity.BaseEntity;
import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.core.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/15 20:00
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends LoggingSupport
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
    public int logicallyDeleteByPk(Serializable pk) {
        return this.baseMapper.logicallyDeleteByPk(pk);
    }

    @Override
    public int insertBatch(List<T> list) {
        List<T> insertList = new ArrayList<>();

        int num = 0;

        for (int i = 0; i < list.size(); i++) {
            insertList.add(list.get(i));
            if (i % Constants.Default.BATCH_SIZE == 0 && i > 0) {
                num += this.baseMapper.insertBatch(insertList);
                insertList.clear();
            }
        }

        if (!CollectionUtils.isEmpty(insertList)) {
            num += this.baseMapper.insertBatch(insertList);
        }

        return num;
    }

    @Override
    public int updateBatch(List<T> list) {
        List<T> updateList = new ArrayList<>();

        int num = 0;

        for (int i = 0; i < list.size(); i++) {
            updateList.add(list.get(i));
            if (i % Constants.Default.BATCH_SIZE == 0 && i > 0) {
                num += this.baseMapper.updateBatch(updateList);
                updateList.clear();
            }
        }

        if (!CollectionUtils.isEmpty(updateList)) {
            num += this.baseMapper.updateBatch(updateList);
        }

        return num;
    }

    @Override
    public PageInfo<T> page(PageSearchDTO pageSearchDTO) {
        PageInfo<T> pageInfo = PageHelper.startPage(pageSearchDTO.getPageNumber(), pageSearchDTO.getPageSize())
                .doSelectPageInfo(() -> this.baseMapper.selectPageList(pageSearchDTO)
                );
        return pageInfo;
    }
}
