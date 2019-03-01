package com.newtouch.buglifecycle.service;


import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.component.PieVO;
import com.newtouch.buglifecycle.entity.home.HomeBaseDataVO;
import com.newtouch.buglifecycle.entity.home.HomeDashBoardVO;
import com.newtouch.buglifecycle.entity.home.HomePieVO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.common.entity.base.Page;

import java.util.List;

/**
 * @author lgyu
 * @date 2019/2/20 15:29
 */
public interface HomeService {

    HomeBaseDataVO getBaseData();

    HomeDashBoardVO getHomeDashBoardData(SystemDTO systemDTO);

    void setDashBoardThreshold(String json);

    HomePieVO getUnsolvedPie(SystemDTO systemDTO);

    List<PieVO> getUnsolvedPieByHour(SystemDTO systemDTO);

    HomePieVO getBugPercent(SystemDTO systemDTO);

    List<PieVO> getBugPercentByHour(SystemDTO systemDTO);

    Page<UnsolvedBugDetialVO> getDetailDataByPie(SystemDTO systemDTO,Page page);
}
