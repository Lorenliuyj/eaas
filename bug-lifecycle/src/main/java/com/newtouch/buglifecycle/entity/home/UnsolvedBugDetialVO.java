package com.newtouch.buglifecycle.entity.home;

import java.io.Serializable;

public class UnsolvedBugDetialVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  bug_id
     */
    private int id;

    /**
     * 系统名字
     */
    private String projectName;

    /**
     * 距提出时间-小时
     */
    private int putHours;

    /**
     * bug类型
     */
    private String type;

    /**
     * bug标题
     */
    private String title;

    /**
     * 创建人
     */
    private String openedBy;

    /**
     * 指派人
     */
    private String assignedTo;

    /**
     * 解决人
     */
    private String resolvedBy;

    /**
     * 方案
     */
    private String resolution;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getPutHours() {
        return putHours;
    }

    public void setPutHours(int putHours) {
        this.putHours = putHours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(String openedBy) {
        this.openedBy = openedBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(String resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
