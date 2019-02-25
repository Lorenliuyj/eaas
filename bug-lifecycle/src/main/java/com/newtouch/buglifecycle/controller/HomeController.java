package com.newtouch.buglifecycle.controller;

import com.newtouch.buglifecycle.entity.*;
import com.newtouch.buglifecycle.entity.home.HomeDashBoardVO;
import com.newtouch.buglifecycle.service.BugsService;
import com.newtouch.buglifecycle.service.DefectThanFiveService;
import com.newtouch.buglifecycle.service.HomeService;
import com.newtouch.buglifecycle.service.UnsolvedBugDetialService;
import com.newtouch.common.entity.base.ResponseVO;
import com.newtouch.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页Controller
 * @author lgyu
 * @date 2019/2/20 11:01
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    BugsService bugsService;

    @Autowired
    UnsolvedBugDetialService unsolvedBugDetialService;

    @Autowired
    DefectThanFiveService defectThanFiveService;

    @RequestMapping("dashBoardData")
    public ResponseVO getdashBoardData(@RequestParam(required=false)String systemName,
                                       @RequestParam(required=false)String version){
        System.out.print(systemName);
        System.out.print(version);
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
    @RequestMapping("versions")
    public ResponseVO getVersions(){
        List<String> list =  new ArrayList<>();
        list.add("20181225");
        list.add("20181223");
        return  ResponseUtil.successResponse(list);
    }
    @RequestMapping("systems")
    public ResponseVO getSystem(){
        List<String> list =  new ArrayList<>();
        list.add("受理平台1");
        list.add("账管系统1");
        return ResponseUtil.successResponse(list);
    }

    @GetMapping(value = "/tableFor48UnDeal")
    public ResponseVO get48UnsolvedBug(@RequestParam(required=false)String systemName,
                                       @RequestParam(required=false)String version,
                                       @RequestParam(required=false)String isUnDeal){
        SystemVO systemVO = new SystemVO();
        systemVO.setSystemName(systemName);
        systemVO.setVersion(version);
        List<BugsInfoVO> list = bugsService.tableFor48UnDeal(systemVO);
        return ResponseUtil.successResponse(list);
    }

    @GetMapping(value = "/tableForRank10")
    public ResponseVO tableForRank10(@RequestParam(required=false)String systemName,
                                     @RequestParam(required=false)String version,
                                     @RequestParam(required=false)String isUnDeal){
        SystemVO systemVO = new SystemVO();
        systemVO.setSystemName(systemName);
        systemVO.setVersion(version);
        List<BugsInfoVO> list = bugsService.tableForRank10(systemVO);
        return ResponseUtil.successResponse(list);
    }

    @GetMapping(value = "/tableFor48UnDealDetail")
    public ResponseVO tableFor48UnDealDetail(@RequestParam(required=false)String systemName,
                                             @RequestParam(required=false)String version,
                                             @RequestParam(required=false)Boolean unDeal,
                                             @RequestParam(required=false)String account){
        SystemVO systemVO = new SystemVO();
        systemVO.setSystemName(systemName);
        systemVO.setVersion(version);
        systemVO.setAccount(account);
        systemVO.setUnDeal(unDeal);
        List<UnsolvedBugDetialVO> list = bugsService.tableForUnDealDetail(systemVO);
        return ResponseUtil.successResponse(list);
    }

    @GetMapping(value = "/tableForRank10Detail")
    public ResponseVO tableFor48UnDealDetail(@RequestParam(required=false)String systemName,
                                             @RequestParam(required=false)String version,
                                             @RequestParam(required=false)String account){
        SystemVO systemVO = new SystemVO();
        systemVO.setSystemName(systemName);
        systemVO.setVersion(version);
        systemVO.setAccount(account);
        List<UnsolvedBugDetialVO> list = bugsService.tableForUnDealDetail(systemVO);
        return ResponseUtil.successResponse(list);
    }
    @RequestMapping("/tableDataForCQOver5")
    public ResponseVO tableDataForCQOver5(@RequestParam(required=false)String systemName,
                              @RequestParam(required=false)String version,
                              @RequestParam(required=false)boolean unDeal) {
        if(unDeal) {
            SystemVO systemVO = new SystemVO();
            systemVO.setSystemName(systemName);
            systemVO.setVersion(version);
            systemVO.setUnDeal(unDeal);
            List<UnsolvedBugDetialVO> result = unsolvedBugDetialService.findAll(systemVO);
            return ResponseUtil.successResponse(result);
        }else {
            List<DefectThanFiveVO> result = defectThanFiveService.findThan5Bug(systemName,version,unDeal);
            return ResponseUtil.successResponse(result);
        }
    }

    @RequestMapping("/tableDataForCQOver5Detail")
    public ResponseVO tableDataForCQOver5Detail(@RequestParam(required=false)String id) {
        SystemVO systemVO = new SystemVO();
        systemVO.setStoryId(id);
        List<UnsolvedBugDetialVO> result = unsolvedBugDetialService.findBugDetail(systemVO);
        return ResponseUtil.successResponse(result);

    }
}
