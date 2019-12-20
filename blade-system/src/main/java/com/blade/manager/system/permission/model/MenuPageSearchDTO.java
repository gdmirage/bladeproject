package com.blade.manager.system.permission.model;

import java.io.Serializable;
import com.blade.core.model.request.PageSearchDTO;
import java.time.LocalDateTime;

/**
 * <p>
 *  分页查询条件
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public class MenuPageSearchDTO extends PageSearchDTO {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Long id;


    /**
     * 创建人
     */
    private String creator;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 修改人
     */
    private String modifier;


    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


    /**
     * 是否删除。0-未删除。1-已删除
     */
    private Integer isDelete;


    /**
     * 菜单名称
     */
    private String menuName;


    /**
     * 组件
     */
    private String component;


    /**
     * 链接地址
     */
    private String path;


    /**
     * 图标
     */
    private String icon;


    /**
     * 上级菜单ID
     */
    private Long pid;


    /**
     * 是否隐藏
     */
    private String hidden;


    /**
     * 权限代码
     */
    private String permissionCode;


    /**
     * 类型
     */
    private String type;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public LocalDateTime getModifyTime() {
        return this.modifyTime;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getComponent() {
        return this.component;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getHidden() {
        return this.hidden;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
