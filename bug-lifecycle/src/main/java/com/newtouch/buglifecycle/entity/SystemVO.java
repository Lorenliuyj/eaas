package com.newtouch.buglifecycle.entity;

/**
 * @author lgyu
 * @date 2019/2/21 16:54
 */
public class SystemVO {
    /**系统名称**/
    private  String systemName;
    /**版本号**/
    private String version;
    /**用户**/
    private String account;
    /**标识**/
    private Boolean unDeal;
    /**故事id**/
    private String storyId;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getUnDeal() {
        return unDeal;
    }

    public void setUnDeal(Boolean unDeal) {
        this.unDeal = unDeal;
    }
}
