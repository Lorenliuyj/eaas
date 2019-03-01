package com.newtouch.buglifecycle.controller;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.BugsService;
import com.newtouch.buglifecycle.service.DefectThanFiveService;
import com.newtouch.buglifecycle.service.HomeService;
import com.newtouch.buglifecycle.service.UnsolvedBugDetialService;
import com.newtouch.common.entity.base.Page;
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
     * @param systemDTO
     * @return
     */
    @GetMapping(value = "dashBoardData")
    public ResponseVO getdashBoardData(SystemDTO systemDTO){
        return ResponseUtil.successResponse(homeService.getHomeDashBoardData(systemDTO));
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
     * @param systemDTO
     * @return
     */
    @GetMapping("getUnsolve")
    public ResponseVO getUnsolve(SystemDTO systemDTO){
        return ResponseUtil.successResponse(homeService.getUnsolvedPie(systemDTO));
    }

    /**
     * 获取未解决数据按小时划分百分比
     * @param systemDTO
     * @return
     */
    @GetMapping("getUnsolveByHour")
    public ResponseVO getUnsolveByHour(SystemDTO systemDTO){
        return ResponseUtil.successResponse(homeService.getUnsolvedPieByHour(systemDTO));
    }

    /**
     * 获取缺陷占比
     * @param systemDTO
     * @return
     */
    @GetMapping("getBugPercent")
    public ResponseVO getBugPercent(SystemDTO systemDTO){
        return ResponseUtil.successResponse(homeService.getBugPercent(systemDTO));
    }

    /**
     * 获取按时间缺陷占比
     * @param systemDTO
     * @return
     */
    @PostMapping("getBugPercentHour")
    public ResponseVO getBugPercentHour(SystemDTO systemDTO){
        return ResponseUtil.successResponse(homeService.getBugPercentByHour(systemDTO));
    }

    /**
     * 缺陷详细信息列表
     * @param systemDTO
     * @return
     */
    @GetMapping("getDetailDataByPie")
    public ResponseVO getDetailDataByPie(SystemDTO systemDTO, Page page){
        //SystemDTO systemDTO = new SystemDTO(systemId,versionId,unDeal);
        return ResponseUtil.successResponse(homeService.getDetailDataByPie(systemDTO,page));
    }

    /**
     * 48小時未解决的bug
     * @param systemDTO
     * @return
     */
    @GetMapping(value = "/tableFor48UnDeal")
    public ResponseVO get48UnsolvedBug(SystemDTO systemDTO){
        List<BugsInfoVO> list = bugsService.tableFor48UnDeal(systemDTO);
        return ResponseUtil.successResponse(list);
    }

    /**
     * 未解决缺陷排名前十
     * @param systemDTO
     * @return
     */
    @GetMapping(value = "/tableForRank10")
    public ResponseVO tableForRank10(SystemDTO systemDTO){
        List<BugsInfoVO> list = bugsService.tableForRank10(systemDTO);
        return ResponseUtil.successResponse(list);
    }

    /**
     * 48小時未解决的bug详情
     * @param systemName
     * @param version
     * @param unDeal
     * @param account
     * @return
     */
//    @GetMapping(value = "/tableFor48UnDealDetail")
//    public ResponseVO tableFor48UnDealDetail(@RequestParam(required=false)String systemName,
//                                             @RequestParam(required=false)String version,
//                                             @RequestParam(required=false)Boolean unDeal,
//                                             @RequestParam(required=false)String account){
//        SystemDTO systemVO = new SystemDTO(systemName,version,unDeal);
//        systemVO.setAccount(account);
//        List<UnsolvedBugDetialVO> list = bugsService.tableForUnDealDetail(systemVO);
//        return ResponseUtil.successResponse(list);
//    }

    /**
     * 未解决缺陷排名前十详情
     * @param systemId
     * @param versionId
     * @param account
     * @return
     */
//    @GetMapping(value = "/tableForRank10Detail")
//    public ResponseVO tableFor48UnDealDetail(@RequestParam(required=false)String systemId,
//                                             @RequestParam(required=false)String versionId,
//                                             @RequestParam(required=false)String account){
//        SystemDTO systemVO = new SystemDTO();
//        systemVO.setSystemId(systemId);
//        systemVO.setVersionId(versionId);
//        systemVO.setAccount(account);
//        List<UnsolvedBugDetialVO> list = bugsService.tableForUnDealDetail(systemVO);
//        return ResponseUtil.successResponse(list);
//    }

    /**
     * 缺陷超5个的cq单明细
     * js  js
     * @param systemId
     * @param versionId
     * @param unDeal
     * @return
     */
//    @RequestMapping("/tableDataForCQOver5")
//    public ResponseVO tableDataForCQOver5(@RequestParam(required=false)String systemId,
//                              @RequestParam(required=false)String versionId,
//                              @RequestParam(required=false)boolean unDeal) {
//            SystemDTO systemVO = new SystemDTO(systemId,versionId,unDeal);
//            List<DefectThanFiveVO> result = defectThanFiveService.findThan5Bug(systemVO);
//            return ResponseUtil.successResponse(result);
//    }

    /**
     * 未解决返工缺陷列表
     * js  js
     * @param systemId
     * @param versionId
     * @param unDeal
     * @return
     */
//    @RequestMapping("/tableDataForUnsolveBug")
//    public ResponseVO tableDataForUnsolveBug(@RequestParam(required=false)String systemId,
//                                          @RequestParam(required=false)String versionId,
//                                          @RequestParam(required=false)boolean unDeal) {
//            SystemDTO systemVO = new SystemDTO(systemId,versionId,unDeal);
//            List<UnsolvedBugDetialVO> result = unsolvedBugDetialService.findAll(systemVO);
//            return ResponseUtil.successResponse(result);
//    }

    /**
     * 缺陷超5个的cq单明细的具体详情
     * js
     * @param id
     * @return
     */
    @RequestMapping("/tableDataForCQOver5Detail")
    public ResponseVO tableDataForCQOver5Detail(@RequestParam(required=false)String id) {
        SystemDTO systemVO = new SystemDTO();
        systemVO.setStoryId(id);
        List<UnsolvedBugDetialVO> result = unsolvedBugDetialService.findBugDetail(systemVO);
        return ResponseUtil.successResponse(result);
    }
}
