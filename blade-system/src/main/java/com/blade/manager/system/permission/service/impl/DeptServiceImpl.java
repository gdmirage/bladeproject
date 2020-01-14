package com.blade.manager.system.permission.service.impl;

import com.blade.core.constant.Constants;
import com.blade.core.page.PageInfo;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.mapper.DeptMapper;
import com.blade.manager.system.permission.model.dept.DeptListSearchDTO;
import com.blade.manager.system.permission.model.dept.DeptListTreeVO;
import com.blade.manager.system.permission.model.dept.DeptPageSearchDTO;
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
    public PageInfo<DeptListTreeVO> getDeptListTree(DeptListSearchDTO searchDTO) {
        List<Dept> deptList = super.baseMapper.selectList(searchDTO);

        // 为了迎合前端的统一封装，转成pageInfo的格式
        PageInfo<DeptListTreeVO> pageInfo = new PageInfo<>();

        pageInfo.setPageNumber(0);
        pageInfo.setPageSize(9999);
        List<DeptListTreeVO> deptListTreeList = this.buildDeptListTree(deptList);
        pageInfo.setRecordList(deptListTreeList);
        pageInfo.setTotalCount(deptListTreeList.size());

        return pageInfo;
    }

    private List<DeptListTreeVO> buildDeptListTree(List<Dept> deptList) {
        List<DeptListTreeVO> deptTreeVOList = new ArrayList<>();

        if (this.containsRootId(deptList)) {
            deptTreeVOList = this.buildDeptListTree(deptList, 0L);
        } else {
            deptTreeVOList = this.buildDeptList(deptList);
        }
        return deptTreeVOList;
    }

    /**
     * 判断是否有root 节点的ID
     *
     * @param deptList {@link List<Dept>}
     * @return true or false
     */
    private boolean containsRootId(List<Dept> deptList) {
        for (Dept dept : deptList) {
            if (dept.getPid() == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 构建列表树形结构
     *
     * @param deptList {@link List<Dept>}
     * @param parentId pid
     * @return {@link List<DeptListTreeVO>}
     */
    private List<DeptListTreeVO> buildDeptListTree(List<Dept> deptList, Long parentId) {
        List<DeptListTreeVO> deptListTreeVOList = new ArrayList<>();

        if (CollectionUtils.isEmpty(deptList)) {
            return deptListTreeVOList;
        }

        deptList.forEach(dept -> {
            if (Objects.equals(dept.getPid(), parentId)) {
                // 根节点
                DeptListTreeVO rootNode = this.dept2DeptListTreeVO(dept);

                List<DeptListTreeVO> children = this.buildDeptListTree(deptList, dept.getId());
                if (!CollectionUtils.isEmpty(children)) {
                    rootNode.setChildren(children);
                }
                deptListTreeVOList.add(rootNode);
            }
        });

        return deptListTreeVOList;
    }

    /**
     * 构建列表
     *
     * @param deptList {@link List<Dept>}
     * @return {@link List<DeptListTreeVO>}
     */
    private List<DeptListTreeVO> buildDeptList(List<Dept> deptList) {
        List<DeptListTreeVO> deptTreeVOList = new ArrayList<>();

        deptList.forEach(dept -> {
            deptTreeVOList.add(this.dept2DeptListTreeVO(dept));
        });

        return deptTreeVOList;
    }

    private DeptListTreeVO dept2DeptListTreeVO(Dept dept) {
        DeptListTreeVO deptListTreeVO = new DeptListTreeVO();
        deptListTreeVO.setId(dept.getId());
        deptListTreeVO.setPid(dept.getPid());
        deptListTreeVO.setCreateTime(dept.getCreateTime());
        deptListTreeVO.setDeptName(dept.getDeptName());
        deptListTreeVO.setStatus(dept.getStatus());
        deptListTreeVO.setLabel(dept.getDeptName());

        return deptListTreeVO;
    }

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