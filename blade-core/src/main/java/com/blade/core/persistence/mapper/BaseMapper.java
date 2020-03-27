package com.blade.core.persistence.mapper;

import com.blade.core.model.request.PageSearchDTO;
import org.apache.ibatis.annotations.Param;

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
     * @param id 主键
     * @return delete num
     */
    int logicallyDeleteByPk(Serializable id);

    /**
     * 分页list查询
     *
     * @param searchDTO {@link PageSearchDTO}
     * @return page data {@link List<T>}
     */
    List<T> selectPageList(@Param("searchDTO") PageSearchDTO searchDTO);
}
