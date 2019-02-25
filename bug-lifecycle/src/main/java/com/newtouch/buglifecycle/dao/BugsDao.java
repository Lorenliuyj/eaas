package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.BugsInfoVO;
import com.newtouch.buglifecycle.entity.SystemVO;
import com.newtouch.buglifecycle.entity.UnsolvedBugDetialVO;

import java.util.List;


/**
 * @author lgyu
 * @date 2019/2/20 16:06
 */
public interface BugsDao {

    /**
     * 48小时未解决的bug
     * @param systemVO
     * @return
     */
    List<BugsInfoVO> tableFor48UnDeal(SystemVO systemVO);


    List<UnsolvedBugDetialVO> tableForUnDealDetail(SystemVO systemVO);

    /**
     * bug数排名前十的
     * @param systemVO
     * @return
     */
    List<BugsInfoVO> tableForRank10(SystemVO systemVO);

}
