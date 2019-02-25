package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;

import java.util.List;

public interface BugsService {
    List<BugsInfoVO> tableFor48UnDeal(SystemDTO systemVO);

    List<BugsInfoVO> tableForRank10(SystemDTO systemVO);

    List<UnsolvedBugDetialVO> tableForUnDealDetail(SystemDTO systemVO);
}
