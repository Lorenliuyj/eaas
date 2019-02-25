package com.newtouch.buglifecycle.entity.home;

import com.newtouch.buglifecycle.entity.component.ComboxVO;

import java.util.List;

/**
 * home页面基础参数VO
 * @author lgyu
 * @date 2019/2/22 16:11
 */
public class HomeBaseDataVO {

    private List<ComboxVO> versionList;

    private List<ComboxVO> systemList;

    public List<ComboxVO> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<ComboxVO> versionList) {
        this.versionList = versionList;
    }

    public List<ComboxVO> getSystemList() {
        return systemList;
    }

    public void setSystemList(List<ComboxVO> systemList) {
        this.systemList = systemList;
    }
}
