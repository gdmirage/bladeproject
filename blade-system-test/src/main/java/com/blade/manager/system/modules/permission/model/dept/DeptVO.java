package com.blade.manager.system.modules.permission.model.dept;

/**
 * @author blade
 * 2019/10/8 10:40
 */
public class DeptVO {

    private Long id;

    private String name;

    private Long pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
