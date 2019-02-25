package com.newtouch.buglifecycle.entity.home;

import com.newtouch.buglifecycle.entity.component.DashBoardVO;

/**
 * 主页仪表盘VO
 * 返工 24小时未解决 48小时未解决
 * @author lgyu
 * @date 2019/2/20 11:15
 */
public class HomeDashBoardVO {

    private DashBoardVO dashBoardRedev;

    private DashBoardVO dashBoard24Hour;

    private DashBoardVO dashBoard48Hour;

    public DashBoardVO getDashBoardRedev() {
        return dashBoardRedev;
    }

    public void setDashBoardRedev(DashBoardVO dashBoardRedev) {
        this.dashBoardRedev = dashBoardRedev;
    }

    public DashBoardVO getDashBoard24Hour() {
        return dashBoard24Hour;
    }

    public void setDashBoard24Hour(DashBoardVO dashBoard24Hour) {
        this.dashBoard24Hour = dashBoard24Hour;
    }

    public DashBoardVO getDashBoard48Hour() {
        return dashBoard48Hour;
    }

    public void setDashBoard48Hour(DashBoardVO dashBoard48Hour) {
        this.dashBoard48Hour = dashBoard48Hour;
    }
}
