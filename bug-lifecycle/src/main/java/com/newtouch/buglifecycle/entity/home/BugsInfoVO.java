package com.newtouch.buglifecycle.entity.home;

/**
 * @author lgyu
 * @date 2019/2/21 15:49
 */
public class BugsInfoVO {
    private String system;
    private String version;
    private String userName;
    private Integer bugsNum;
    private String account;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBugsNum() {
        return bugsNum;
    }

    public void setBugsNum(Integer bugsNum) {
        this.bugsNum = bugsNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
