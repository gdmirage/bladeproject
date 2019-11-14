package com.blade.manager.system.modules.permission.model.permission;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author blade
 * 2019/11/14 10:16
 */
public class PermissionListVO implements Serializable {
    private static final long serialVersionUID = 7476433612613469504L;

    private Long id;

    /**
     * 别名
     */
    private String alias;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级权限
     */
    private Integer pid;

    private List<PermissionListVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<PermissionListVO> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionListVO> children) {
        this.children = children;
    }
}
