package com.newtouch.buglifecycle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.newtouch.buglifecycle.dao.HomeDao;
import com.newtouch.buglifecycle.dao.UnsolvedBugDetialDao;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.component.PieVO;
import com.newtouch.buglifecycle.entity.home.HomeBaseDataVO;
import com.newtouch.buglifecycle.entity.home.HomeDashBoardVO;
import com.newtouch.buglifecycle.entity.home.HomePieVO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.HomeService;
import com.newtouch.common.entity.base.Page;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author lgyu
 * @date 2019/2/20 16:01
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    HomeDao homeDao;

    @Resource
    UnsolvedBugDetialDao unsolvedBugDetialDao;

    @Override
    public HomeBaseDataVO getBaseData() {
        HomeBaseDataVO homeBaseDataVO = new HomeBaseDataVO();
        homeBaseDataVO.setSystemList(homeDao.getSystemList());
        homeBaseDataVO.setVersionList(homeDao.getVersionList());
        return homeBaseDataVO;
    }

    @Override
    public HomeDashBoardVO getHomeDashBoardData(SystemDTO systemDTO) {
        systemDTO.setStatus("active");
        int redevCount = homeDao.getRedevBugCount(systemDTO);
        int unsolve24Hour = homeDao.get24BugCount(systemDTO);
        int unsolve48Hour = homeDao.get48BugCount(systemDTO);

        HomeDashBoardVO vo = getDashBoardThreshold();
        vo.getDashBoardRedev().setCurrentValue(redevCount);
        vo.getDashBoard24Hour().setCurrentValue(unsolve24Hour);
        vo.getDashBoard48Hour().setCurrentValue(unsolve48Hour);
        return vo;
    }

    @Override
    public void setDashBoardThreshold(String json) {
        File file = new File("./file/threshold.json");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(json);
        } catch (IOException e) {
        }finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public HomePieVO getUnsolvedPie(SystemDTO systemDTO) {
        systemDTO.setStatus("active");
        HomePieVO homePieVO = new HomePieVO();
        homePieVO.setPieForSys(homeDao.getBugBySystem(systemDTO));
        homePieVO.setPieForRework(homeDao.getBugByRedev(systemDTO));
        homePieVO.setPieForOver48(homeDao.getBugByOver48(systemDTO));
        return homePieVO;
    }

    @Override
    public List<PieVO> getUnsolvedPieByHour(SystemDTO systemDTO) {
        systemDTO.setStatus("active");
        List<PieVO> list = homeDao.getBugByHour(systemDTO);
        for(PieVO vo:list){
            switch(vo.getKey()){
                case "lt24":
                    vo.setMaxTime("24");
                    break;
                case "gt48":
                    vo.setMinTime("48");
                    break;
                default:
                    vo.setMaxTime("48");
                    vo.setMinTime("24");
                    break;
            }
        }
        return list;
    }

    @Override
    public HomePieVO getBugPercent(SystemDTO systemDTO) {
        HomePieVO homePieVO = new HomePieVO();
        homePieVO.setPieForSys(homeDao.getBugBySystem(systemDTO));
        homePieVO.setPieForRework(homeDao.getBugByRedev(systemDTO));
        homePieVO.setPieForOver48(homeDao.getBugByOver48(systemDTO));
        return homePieVO;
    }

    @Override
    public List<PieVO> getBugPercentByHour(SystemDTO systemDTO) {
        List<PieVO> list = homeDao.getBugByHour(systemDTO);
        for(PieVO vo:list){
            switch(vo.getKey()){
                case "lt24":
                    vo.setMaxTime("24");
                    break;
                case "gt48":
                    vo.setMinTime("48");
                    break;
                default:
                    vo.setMaxTime("48");
                    vo.setMinTime("24");
                    break;
            }
        }
        return list;
    }

    @Override
    public Page<UnsolvedBugDetialVO> getDetailDataByPie(SystemDTO systemDTO, Page page) {
        page.init();
        page.setList(unsolvedBugDetialDao.findBugDetailPage(systemDTO,page));
        return page;
    }


    public HomeDashBoardVO getDashBoardThreshold(){
        File file = new File("./file/threshold.json");
        HomeDashBoardVO vo = null;
        try {
            String jsonStr = FileUtils.readFileToString(file,"utf-8");
            vo = JSONObject.parseObject(jsonStr, HomeDashBoardVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vo;
    }
}
