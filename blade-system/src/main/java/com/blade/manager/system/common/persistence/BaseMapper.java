package com.blade.manager.system.common.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * 基本数据库操作
 *
 * @author blade
 * 2019/11/18 11:26
 */
public interface BaseMapper<T> extends CrudMapper<T> {

    /**
     * 批量插入
     *
     * @param list list
     * @return insert num
     */
    int insertBatch(List<T> list);

    /**
     * 逻辑删除
     *
     * @param pk 主键
     * @return delete num
     */
    int logicallyDeleteByPk(Serializable pk);
}
