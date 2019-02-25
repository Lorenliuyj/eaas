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
    public HomeDashBoardVO getHomeDashBoardData(String system, String version) {
        int redevCount = homeDao.getRedevBugCount(system, version,"active");
        int unsolve24Hour = homeDao.get24BugCount(system, version,"active");
        int unsolve48Hour = homeDao.get48BugCount(system, version,"active");

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
    public HomePieVO getUnsolvedPie(String system, String version) {
        HomePieVO homePieVO = new HomePieVO();
        homePieVO.setPieForSys(homeDao.getBugBySystem(system, version,"active"));
        homePieVO.setPieForRework(homeDao.getBugByRedev(system, version,"active"));
        homePieVO.setPieForOver48(homeDao.getBugByOver48(system, version,"active"));
        return homePieVO;
    }

    @Override
    public List<PieVO> getUnsolvedPieByHour(String system, String version) {
        return homeDao.getBugByHour(system, version,"active");
    }

    @Override
    public HomePieVO getBugPercent(String system, String version) {
        HomePieVO homePieVO = new HomePieVO();
        homePieVO.setPieForSys(homeDao.getBugBySystem(system, version,null));
        homePieVO.setPieForRework(homeDao.getBugByRedev(system, version,null));
        homePieVO.setPieForOver48(homeDao.getBugByOver48(system, version,null));
        return homePieVO;
    }

    @Override
    public List<PieVO> getBugPercentByHour(String system, String version) {
        return homeDao.getBugByHour(system, version,null);
    }

    @Override
    public List<UnsolvedBugDetialVO> getDetailDataByPie(SystemDTO systemDTO) {
        return unsolvedBugDetialDao.findBugDetail(systemDTO);
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
