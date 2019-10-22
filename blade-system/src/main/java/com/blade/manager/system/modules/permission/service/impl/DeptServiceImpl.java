package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.mapper.DeptMapper;
import com.blade.manager.system.modules.permission.model.dept.DeptTreeVO;
import com.blade.manager.system.modules.permission.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-10-14
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    public List<DeptTreeVO> findDeptTree(){
        List<DeptTreeVO> parentNodes = super.baseMapper.selectDeptByPid(0);

        parentNodes.forEach(parentNode -> this.findChildren(parentNode));

        return parentNodes;
    }

    private void findChildren(DeptTreeVO node){
        List<DeptTreeVO> children = super.baseMapper.selectDeptByPid(node.getId());
        node.setChildren(children);
        if (CollectionUtils.isEmpty(children)) {
            return;
        }

        children.forEach(childNode -> this.findChildren(childNode));
    }
}