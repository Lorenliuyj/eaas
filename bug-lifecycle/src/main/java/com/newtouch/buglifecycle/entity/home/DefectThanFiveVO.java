package com.newtouch.buglifecycle.entity.home;

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
     * 版本id
     */
    private String versionId;

    /**
     * 系统id
     */
    private String systemId;

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

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
