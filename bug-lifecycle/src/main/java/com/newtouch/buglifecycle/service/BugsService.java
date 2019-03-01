package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.common.entity.base.Page;

import java.util.List;

public interface BugsService {
    List<BugsInfoVO> tableFor48UnDeal(SystemDTO systemVO);

    List<BugsInfoVO> tableForRank10(SystemDTO systemVO);

    Page<UnsolvedBugDetialVO> tableForUnDealDetail(SystemDTO systemVO, Page page);

    Page<UnsolvedBugDetialVO> tableFor48UnDealDetail(SystemDTO systemVO, Page page);

    Page<UnsolvedBugDetialVO> tableForRank10Detail(SystemDTO systemVO, Page page);
}
