package com.blade.manager.system.permission.model.job;

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
public class JobPageSearchDTO extends PageSearchDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间-开始
     */
    private String startTime;

    /**
     * 创建时间-结束
     */
    private String endTime;

    /**
     * 岗位名称
     */
    private String jobName;


    /**
     * 状态(1、enabled 2、disabled)
     */
    private String status;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
