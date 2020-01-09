package com.blade.manager.system.permission.service.impl;

import com.blade.core.constant.Constants;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.mapper.DeptMapper;
import com.blade.manager.system.permission.model.dept.DeptTreeVO;
import com.blade.manager.system.permission.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public List<DeptTreeVO> getDeptTree() {
        List<Dept> deptList = super.baseMapper.findAllEnabledDept(Constants.Status.ENABLED);
//        super.logger.info("dept tree is : {}", this.buildDeptTree(deptList, 0L));
        return this.buildDeptTree(deptList, 0L);
    }

    private List<DeptTreeVO> buildDeptTree(List<Dept> deptList, Long parentId) {
        List<DeptTreeVO> deptTreeVOList = new ArrayList<>();

        if (CollectionUtils.isEmpty(deptList)) {
            return deptTreeVOList;
        }

        deptList.forEach(dept -> {
            if (Objects.equals(dept.getPid(), parentId)) {
                // 根节点
                DeptTreeVO rootNode = new DeptTreeVO();
                rootNode.setId(dept.getId());
                rootNode.setLabel(dept.getDeptName());

                List<DeptTreeVO> children = this.buildDeptTree(deptList, dept.getId());
                if (!CollectionUtils.isEmpty(children)) {
                    rootNode.setChildren(children);
                }
                deptTreeVOList.add(rootNode);
            }
        });

        return deptTreeVOList;
    }
}