package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;

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
    List<BugsInfoVO> tableFor48UnDeal(SystemDTO systemVO);


    List<UnsolvedBugDetialVO> tableForUnDealDetail(SystemDTO systemVO);

    /**
     * bug数排名前十的
     * @param systemVO
     * @return
     */
    List<BugsInfoVO> tableForRank10(SystemDTO systemVO);

}
