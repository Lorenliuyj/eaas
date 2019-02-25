package com.newtouch.buglifecycle.entity;

import java.io.Serializable;

/**
 * 缺陷超过5个的系统、版本、CQ单对应的Bug
 *
 */
public class DefectThanFiveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  zt_story  id
     */
    private int id;

    /**
     * 系统名字
     */
    private String projectName;

    /**
     * 需求名称
     */
    private String title;

    /**
     * 版本
     */
    private String version;

    /**
     * bug数
     */
    private String bugSum;

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

    public String getBugSum() {
        return bugSum;
    }

    public void setBugSum(String bugSum) {
        this.bugSum = bugSum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
