package com.newtouch.buglifecycle.controller;

import com.newtouch.buglifecycle.entity.DashBoardVO;
import com.newtouch.buglifecycle.entity.home.HomeDashBoardVO;
import com.newtouch.common.entity.base.ResponseVO;
import com.newtouch.common.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页Controller
 * @author lgyu
 * @date 2019/2/20 11:01
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @ResponseBody
    @RequestMapping("dashBoardData")
    public ResponseVO getdashBoardData(@RequestParam(required=false)String systemName,
                                       @RequestParam(required=false)String version){

        HomeDashBoardVO homeDashBoardVO = new HomeDashBoardVO();

        DashBoardVO dashBoardVO0 = new DashBoardVO();
        dashBoardVO0.setAction(6);
        dashBoardVO0.setWarnning(15);
        dashBoardVO0.setMax(30);
        dashBoardVO0.setCurrentValue(17);
        DashBoardVO dashBoardVO1 = new DashBoardVO();
        dashBoardVO1.setAction(6);
        dashBoardVO1.setWarnning(15);
        dashBoardVO1.setMax(30);
        dashBoardVO1.setCurrentValue(17);
        DashBoardVO dashBoardVO2 = new DashBoardVO();
        dashBoardVO2.setAction(6);
        dashBoardVO2.setWarnning(15);
        dashBoardVO2.setMax(30);
        dashBoardVO2.setCurrentValue(17);
        homeDashBoardVO.setDashBoardRedev(dashBoardVO0);
        homeDashBoardVO.setDashBoard24Hour(dashBoardVO1);
        homeDashBoardVO.setDashBoard48Hour(dashBoardVO2);
        return ResponseUtil.successResponse(homeDashBoardVO);
    }

}
