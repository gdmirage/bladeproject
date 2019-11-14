package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.Permission;
import com.blade.manager.system.modules.permission.mapper.PermissionMapper;
import com.blade.manager.system.modules.permission.model.permission.PermissionListVO;
import com.blade.manager.system.modules.permission.model.permission.PermissionSearchDTO;
import com.blade.manager.system.modules.permission.model.permission.PermissionTreeVO;
import com.blade.manager.system.modules.permission.service.IPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<PermissionTreeVO> getPermissionTree() {
        List<PermissionListVO> permissionListVOList = this.getPermissionList(new PermissionSearchDTO());
        List<PermissionTreeVO> tree = new ArrayList<>();

        permissionListVOList.forEach(permissionListVO -> {
            tree.add(this.setTreeNode(permissionListVO));
        });

        return tree;
    }

    private PermissionTreeVO setTreeNode(PermissionListVO permissionListVO) {
        PermissionTreeVO permissionTreeVO = new PermissionTreeVO();
        permissionTreeVO.setId(permissionListVO.getId());
        permissionTreeVO.setLabel(permissionListVO.getAlias());
        List<PermissionTreeVO> children = new ArrayList<>();

        permissionListVO.getChildren().forEach(permissionListChild -> {
            PermissionTreeVO child = this.setTreeNode(permissionListChild);
            children.add(child);
        });

        permissionTreeVO.setChildren(children);
        return permissionTreeVO;
    }

    @Override
    public List<PermissionListVO> getPermissionList(PermissionSearchDTO permissionSearchDTO) {
        List<Permission> permissions = super.baseMapper.selectPermissionList(permissionSearchDTO);

        return this.buildTree(permissions);
    }

    private List<PermissionListVO> buildTree(List<Permission> permissions) {
        List<PermissionListVO> rootTree = new ArrayList<>();
        List<PermissionListVO> nodeTree = new ArrayList<>();

        permissions.forEach(permission -> {
            // 根节点
            if (permission.getPid() == 0) {
                PermissionListVO permissionListVO = new PermissionListVO();
                BeanUtils.copyProperties(permission, permissionListVO);

                this.getChildren(permissionListVO);

                rootTree.add(permissionListVO);
            } else {
                PermissionListVO permissionListVO = new PermissionListVO();
                BeanUtils.copyProperties(permission, permissionListVO);

                this.getChildren(permissionListVO);

                nodeTree.add(permissionListVO);
            }
        });

        return CollectionUtils.isEmpty(rootTree) ? nodeTree : rootTree;
    }

    private void getChildren(PermissionListVO parent) {
        List<Permission> permissions = super.baseMapper.selectPermissionListByPid(parent.getId().intValue());

        List<PermissionListVO> children = new ArrayList<>();

        permissions.forEach(permission -> {
            PermissionListVO child = new PermissionListVO();
            BeanUtils.copyProperties(permission, child);
            this.getChildren(child);
            children.add(child);
        });

        parent.setChildren(children);
    }

    @Override
    public List<Permission> selectByRoleId(Long roleId) {
        return this.selectByRoleIds(Collections.singletonList(roleId));
    }

    private List<Permission> selectByRoleIds(List<Long> roleIds) {
        return super.baseMapper.selectByRoleIds(roleIds);
    }
}
