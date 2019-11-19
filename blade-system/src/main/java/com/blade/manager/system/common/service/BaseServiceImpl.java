package com.blade.manager.system.common.service;

import com.blade.manager.system.common.model.request.PageSearchDTO;
import com.blade.manager.system.common.persistence.BaseMapper;
import com.blade.manager.system.common.persistence.entity.BaseEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/15 20:00
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity>
        extends SqlSessionDaoSupport implements IBaseService<T> {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    protected M baseMapper;

    /**
     * Autowired 必须要有
     */
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

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
    public void pageTest(PageSearchDTO pageSearchDTO) {
        List<Object> list = sqlSessionTemplate.selectList(this.baseMapper.getClass().getGenericInterfaces()[0].getTypeName() + "." + "page", pageSearchDTO);
        System.out.println(list.size());
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
