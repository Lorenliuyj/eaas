package com.newtouch.buglifecycle.service;


import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.component.PieVO;
import com.newtouch.buglifecycle.entity.home.HomeBaseDataVO;
import com.newtouch.buglifecycle.entity.home.HomeDashBoardVO;
import com.newtouch.buglifecycle.entity.home.HomePieVO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;

import java.util.List;

/**
 * @author lgyu
 * @date 2019/2/20 15:29
 */
public interface HomeService {

    HomeBaseDataVO getBaseData();

    HomeDashBoardVO getHomeDashBoardData(String systemName, String version);

    void setDashBoardThreshold(String json);

    HomePieVO getUnsolvedPie(String systemName, String version);

    List<PieVO> getUnsolvedPieByHour(String systemName, String version);

    HomePieVO getBugPercent(String systemName, String version);

    List<PieVO> getBugPercentByHour(String systemName, String version);

    List<UnsolvedBugDetialVO> getDetailDataByPie(SystemDTO systemDTO);
}
