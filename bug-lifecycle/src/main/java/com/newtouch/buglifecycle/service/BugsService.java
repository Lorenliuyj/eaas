package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.BugsInfoVO;
import com.newtouch.buglifecycle.entity.SystemVO;
import com.newtouch.buglifecycle.entity.UnsolvedBugDetialVO;

import java.util.List;

public interface BugsService {
    List<BugsInfoVO> tableFor48UnDeal(SystemVO systemVO);

    List<BugsInfoVO> tableForRank10(SystemVO systemVO);

    List<UnsolvedBugDetialVO> tableForUnDealDetail(SystemVO systemVO);
}
