package com.newtouch.buglifecycle.controller;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.BugsService;
import com.newtouch.buglifecycle.service.DefectThanFiveService;
import com.newtouch.buglifecycle.service.HomeService;
import com.newtouch.buglifecycle.service.UnsolvedBugDetialService;
import com.newtouch.common.entity.base.ResponseVO;
import com.newtouch.common.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 主页Controller
 * @author lgyu
 * @date 2019/2/20 11:01
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @Resource
    BugsService bugsService;

    @Resource
    UnsolvedBugDetialService unsolvedBugDetialService;

    @Resource
    DefectThanFiveService defectThanFiveService;

    @Resource
    HomeService homeService;

    /**
     * 获取页面基础参数
     * @return
     */
    @GetMapping("getBaseData")
    public ResponseVO getBaseData(){
        return ResponseUtil.successResponse(homeService.getBaseData());
    }

    /**
     * 获取home页面仪表盘数据
     * @param systemName
     * @param version
     * @return
     */
    @GetMapping("dashBoardData")
    public ResponseVO getdashBoardData(@RequestParam(required=false)String systemName,
                                       @RequestParam(required=false)String version){
        return ResponseUtil.successResponse(homeService.getHomeDashBoardData(systemName, version));
    }

    /**
     * 设置home页面阀值
     * @param json 阀值json对象
     * @return
     */
    @GetMapping("setDashBoardThreshold")
    public ResponseVO setDashBoardThreshold(@RequestParam String json){
        homeService.setDashBoardThreshold(json);
        return ResponseUtil.successResponse(json);
    }

    /**
     * 获取未解决饼图数据
     * @param systemName
     * @param version
     * @return
     */
    @GetMapping("getUnsolve")
    public ResponseVO getUnsolve(@RequestParam(required=false)String systemName,
                                 @RequestParam(required=false)String version){
        return ResponseUtil.successResponse(homeService.getUnsolvedPie(systemName,version));
    }

    /**
     * 获取未解决数据按小时划分百分比
     * @param systemName
     * @param version
     * @return
     */
    @GetMapping("getUnsolveByHour")
    public ResponseVO getUnsolveByHour(@RequestParam(required=false)String systemName,
                                       @RequestParam(required=false)String version){
        return ResponseUtil.successResponse(homeService.getUnsolvedPieByHour(systemName,version));
    }

    /**
     * 获取缺陷占比
     * @param systemName
     * @param version
     * @return
     */
    @GetMapping("getBugPercent")
    public ResponseVO getBugPercent(@RequestParam(required=false)String systemName,
                                    @RequestParam(required=false)String version){
        return ResponseUtil.successResponse(homeService.getBugPercent(systemName,version));
    }

    /**
     * 获取按时间缺陷占比
     * @param systemName
     * @param version
     * @return
     */
    @GetMapping("getBugPercentHour")
    public ResponseVO getBugPercentHour(@RequestParam(required=false)String systemName,
                                        @RequestParam(required=false)String version){
        return ResponseUtil.successResponse(homeService.getBugPercentByHour(systemName,version));
    }

    /**
     * 缺陷详细信息列表
     * @param systemName
     * @param version
     * @param unDeal
     * @return
     */
    @GetMapping("getDetailDataByPie")
    public ResponseVO getDetailDataByPie(@RequestParam(required=false)String systemName,
                                         @RequestParam(required=false)String version,
                                         @RequestParam(required=false)boolean unDeal){
        SystemDTO systemDTO = new SystemDTO(systemName,version,unDeal);
        return ResponseUtil.successResponse(homeService.getDetailDataByPie(systemDTO));
    }

    @GetMapping(value = "/tableFor48UnDeal")
    public ResponseVO get48UnsolvedBug(@RequestParam(required=false)String systemName,
                                       @RequestParam(required=false)String version,
                                       @RequestParam(required=false)String isUnDeal){
        SystemDTO systemVO = new SystemDTO();
        systemVO.setSystemName(systemName);
        systemVO.setVersion(version);
        List<BugsInfoVO> list = bugsService.tableFor48UnDeal(systemVO);
        return ResponseUtil.successResponse(list);
    }

    @GetMapping(value = "/tableForRank10")
    public ResponseVO tableForRank10(@RequestParam(required=false)String systemName,
                                     @RequestParam(required=false)String version,
                                     @RequestParam(required=false)String isUnDeal){
        SystemDTO systemVO = new SystemDTO();
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
        SystemDTO systemVO = new SystemDTO();
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
        SystemDTO systemVO = new SystemDTO();
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
            SystemDTO systemVO = new SystemDTO();
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
        SystemDTO systemVO = new SystemDTO();
        systemVO.setStoryId(id);
        List<UnsolvedBugDetialVO> result = unsolvedBugDetialService.findBugDetail(systemVO);
        return ResponseUtil.successResponse(result);

    }
}
