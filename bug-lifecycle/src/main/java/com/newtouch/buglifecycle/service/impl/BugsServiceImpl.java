package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.BugsDao;
import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.BugsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BugsServiceImpl implements BugsService {

    @Resource
    BugsDao bugsDao;
    @Override
    public List<BugsInfoVO> tableFor48UnDeal(SystemDTO systemVO) {
        return bugsDao.tableFor48UnDeal(systemVO);
    }

    @Override
    public List<BugsInfoVO> tableForRank10(SystemDTO systemVO) {
        return bugsDao.tableForRank10(systemVO);
    }

    @Override
    public List<UnsolvedBugDetialVO> tableForUnDealDetail(SystemDTO systemVO) {
        return bugsDao.tableForUnDealDetail(systemVO);
    }
}
